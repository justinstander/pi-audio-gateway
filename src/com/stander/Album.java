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
	private int mArtistId;
	private List<Music> mMusic = null;
	
	/**
	 * @param file
	 */
	public Album(File file,int index,int artistId) {
		super();
		init(file,index,artistId);
	}
	
	/**
	 * @return
	 */
	public int getArtistId() {
		return mArtistId;
	}
	
	/**
	 * @return
	 */
	public List<Music> getMusic() {
		return mMusic;
	}
	
	/**
	 * @see com.stander.FileItem#toString()
	 */
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder(super.toString());
		mString.append(String.format("%n"));
		int a = mMusic.size();
		for(int i=0;i<a;i++) {
			mString.append(mMusic.get(i).toString());
			mString.append(String.format("%n"));
		}
		return mString.toString();
	}
	
	/**
	 * @see com.stander.FileItem#init(java.io.File)
	 */
	protected void init(File file,int index,int artistId) {
		super.init(file,index);
		
		mArtistId = artistId;
		mMusic = new ArrayList<Music>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++) {
			File item = files[i];
			if( item.isFile() ) {
				String name = item.getName();
				String extension = name.substring(name.lastIndexOf('.')+1);
				if( extension.equals(MP3) ) {
					int musicId = mMusic.size();
					Music musicItem = new Music(item, musicId, artistId, getId());
					readTags(item,musicItem);
					mMusic.add(musicItem);
				}
			}
		}
		
		Collections.sort(mMusic, new CustomComparator());
	}

	/**
	 * @param item
	 * @return
	 */
	private void readTags(File item,Music music) {
		FileInputStream file = null;
		
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
				return;
			}
			
	        byte[] last128 = new byte[128]; 
	        
	        try {
				file.read(last128);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
				return;
			} 
	        
	        String id3 = new String(last128); 
	        String tag = id3.substring(0, 3); 
//	        logger.info("FULL TAG: "+id3);
	        if (tag.equals("TAG")) { 
//	           logger.info("Title: " + id3.substring(3, 32)); 
	        	music.title = id3.substring(3, 32);
//	           logger.info("Artist: " + id3.substring(33, 62)); 
//	           logger.info("Album: " + id3.substring(63, 92)); 
//	           logger.info("Year: " + id3.substring(93, 97));
	        	music.year = id3.substring(93, 97);
//	           logger.info("Comment: "+ id3.substring(97, 126));
	           int zeroByte = last128[125];
//	           logger.info("Zero Byte: "+zeroByte);
	           if( zeroByte == 0 ) {
//	        	   logger.info("Track: "+last128[126]);
	        	   music.trackNumber = last128[126];
//	        	   logger.info("Genre: "+last128[127]);
	           }
	           
	        } else {
	        	logger.info(" does not contain" + " ID3 info.");
	        }
	             
	        try {
				file.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
}
