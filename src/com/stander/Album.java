package com.stander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author justin.stander
 *
 */
public class Album extends FileItem {
	private static final Logger logger = LoggerFactory.getLogger(FilesServlet.class);
	private static final String MP3 = "mp3";
	private static final Object TAG = "TAG";
	private static final String PERIOD = ".";
	private int mArtistId;
	private List<Music> mMusic = null;
	
	/**
	 * @param file
	 */
	public Album(File file) {
		super();
		init(file);
	}
	
	/**
	 * @return
	 */
	public int getArtistId() {
		return mArtistId;
	}
	
	/**
	 * @param id
	 */
	public void setArtistId(int value) {
		mArtistId = value;
	}
	
	/**
	 * @return
	 */
	public List<Music> getMusic() {
		return mMusic;
	}
	
	/**
	 * @see com.stander.FileItem#setId(int)
	 */
	@Override
	public void setId(int value) {
		super.setId(value);
		int count = mMusic.size();
		for(int i=0;i<count;i++) {
			Music music = mMusic.get(i);
			music.setAlbumId(getId());
			music.setArtistId(getArtistId());
		}
	}
	
	/**
	 * @see com.stander.FileItem#toString()
	 */
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder(super.toString());
		mString.append(String.format(LINE_BREAK));
		int a = mMusic.size();
		for(int i=0;i<a;i++) {
			mString.append(mMusic.get(i).toString());
			mString.append(String.format(LINE_BREAK));
		}
		return mString.toString();
	}
	
	/**
	 * @see com.stander.FileItem#init(java.io.File)
	 */
	protected void init(File file) {
		super.init(file);
		
		mMusic = new ArrayList<Music>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++) {
			File item = files[i];
			if( item.isFile() ) {
				String name = item.getName();
				String extension = name.substring(name.lastIndexOf(PERIOD)+1);
				if( extension.equals(MP3) ) {
					Music musicItem = readTags(item);
					mMusic.add(musicItem);
				}
			}
		}
		
		Collections.sort(mMusic, new MusicComparator());
	}

	/**
	 * @param item
	 * @return
	 */
	private Music readTags(File item) {
		FileInputStream file = null;
		Music music = null;
		
		try {
			file = new FileInputStream(item);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} 
		
		if(file != null) {
			int size = (int)item.length(); 
	        
			try {
				file.skip(size - 128);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
				return null;
			}
			
	        byte[] last128 = new byte[128]; 
	        
	        try {
				file.read(last128);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
				return null;
			} 
	        
	        String id3 = new String(last128); 
	        String tag = id3.substring(0, 3);
	        
	        if (tag.equals(TAG)) { 
	        	int zeroByte = last128[125];
		           if( zeroByte == 0 ) {
		        	   int trackNumber = last128[126];
		        	   music = new Music(item, trackNumber-1);
		        	   music.trackNumber = trackNumber;
		           }
	        	music.title = id3.substring(3, 32).trim();
	        	music.year = id3.substring(93, 97).trim();
	        } else {
	        	music = new Music(item,mMusic.size());
	        }
	             
	        try {
				file.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		
		return music;
	}
}
