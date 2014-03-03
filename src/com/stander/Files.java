package com.stander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

/**
 * Servlet implementation class AudioGateway
 */
@WebServlet(description = "Audio file listing", urlPatterns = { "/Files" })
public final class Files extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(Files.class);
	private static final long serialVersionUID = 1L;
	private static final String AUDIO_PATH = "AudioPath";
	private static Model model;

    /**
     * Default constructor. 
     */
    public Files() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.info("init");
		String path = config.getServletContext().getInitParameter(AUDIO_PATH);
		logger.info("Using Audio Path: "+path);
		
		model = Model.getInstance();
		model.init(path);
		
//		logger.info(model.getArtists().toString());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.info("destroy");
		model = null;
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		logger.info("AudioGateway#getServletConfig");
		return super.getServletConfig();
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		logger.info("AudioGateway#getServletInfo");
		return super.getServletInfo(); 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(	
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("service");
		super.service(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("*** doGet");
		String artist = request.getParameter("artist");
		String album = request.getParameter("album");
		String song = request.getParameter("song");
		logger.info("artist: "+artist+" album: "+album+" song: "+song);
		
		if( artist != null && album != null && song != null ) {
			sendFile(artist,album,song,response.getOutputStream());
		} else {
			sendFileList(response.getOutputStream());
		}
	}

	private void sendFileList(ServletOutputStream outputStream) {
		logger.info("sendFileList");
	}

	/**
	 * @param artist
	 * @param album
	 * @param song
	 * @param outputStream
	 */
	private void sendFile(String artist, String album, String song,
			ServletOutputStream outputStream) {
		Model model = Model.getInstance();
		
		int artistId = Integer.parseInt(artist);
		int albumId = Integer.parseInt(album);
		int songId = Integer.parseInt(song);
		
		Music music = model.
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("*** AudioGateway#doPost");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doPut");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doDelete");
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doHead");
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doOptions");
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doTrace");
	}

}
