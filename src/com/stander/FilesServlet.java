package com.stander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AudioGateway
 */
@WebServlet(description = "Audio file listing", urlPatterns = { "/Files" })
public final class FilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(FilesServlet.class);
	private static final String INIT = "init";
	private static final String DESTROY = "destroy";
	private static final String SEND_FILE_LIST = "sendFileList";
	
	private static final String AUDIO_PATH = "AudioPath";
	
	private static final String PARAMETER_ARTIST = "artist";
	private static final String PARAMETER_ALBUM = "album";
	private static final String PARAMETER_SONG = "song";

    /**
     * Default constructor. 
     */
    public FilesServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.info(INIT);
		Model.getInstance().init(config.getServletContext().getInitParameter(AUDIO_PATH));
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.info(DESTROY);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String artist = request.getParameter(PARAMETER_ARTIST);
		String album = request.getParameter(PARAMETER_ALBUM);
		String song = request.getParameter(PARAMETER_SONG);
		
		if( artist != null && album != null && song != null ) {
			sendFile(artist,album,song,response.getOutputStream());
		} else if( artist == null && album == null && song == null ){
			sendFileList(response.getWriter());
		}
	}

	/**
	 * @param output
	 */
	private void sendFileList(PrintWriter output) {
		logger.info(SEND_FILE_LIST);
		Gson gson = new GsonBuilder().create();
		String fileList = gson.toJson(Model.getInstance().getArtists());
		output.println(fileList);
	}

	/**
	 * @param artist
	 * @param album
	 * @param song
	 * @param outputStream
	 */
	private void sendFile(String artist, String album, String song,
			ServletOutputStream outputStream) {
		
		int artistId = Integer.parseInt(artist);
		int albumId = Integer.parseInt(album);
		int songId = Integer.parseInt(song);
		
		Music music = Model.getInstance().
				getArtists().get(artistId).
				getAlbums().get(albumId).
				getMusic().get(songId);
		
		try {
			IOUtils.copy(
				new FileInputStream(new File(music.getPath())),
				outputStream
			);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
