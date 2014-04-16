package com.stander;

import java.io.File;

/**
 * @author Justin Stander (justin.stander@gmail.com)
 *
 */
public class Music extends FileItem {
	private int mArtistId;
	private int mAlbumId;
	private String mPath;
	
	public String title;
	public String year;
	public int trackNumber = -1;

	/**
	 * @param file
	 * @param index
	 */
	public Music(File file,int index) {
		super();
		init(file,index);
	}
	
	/**
	 * @return
	 */
	public int getArtistId() {
		return mArtistId;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public void setArtistId(int value) {
		mArtistId = value;
	}
	
	/**
	 * @return
	 */
	public int getAlbumId() {
		return mAlbumId;
	}
	
	/**
	 * @param id
	 */
	public void setAlbumId(int value) {
		mAlbumId = value;
	}
	
	/**
	 * @return
	 */
	public String getPath() {
		return mPath;
	}
	
	/**
	 * @param file
	 * @param index
	 * @param artistId
	 * @param albumId
	 */
	protected void init(File file,int index) {
		super.init(file);
		mId = index;
		mPath = file.getAbsolutePath();
	}
}
