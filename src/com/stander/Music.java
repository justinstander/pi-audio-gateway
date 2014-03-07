package com.stander;

import java.io.File;

/**
 * @author jstander
 *
 */
public class Music extends FileItem {
	private int mArtistId;
	private int mAlbumId;
	private String mPath;
	
	public String title;
	public String year;
	public int trackNumber;

	/**
	 * @param file
	 * @param index
	 */
	public Music(File file,int index,int artistId,int albumId) {
		super();
		init(file,index,artistId,albumId);
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
	public int getAlbumId() {
		return mAlbumId;
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
	protected void init(File file,int index,int artistId,int albumId) {
		super.init(file,index);
		mArtistId = artistId;
		mAlbumId = albumId;
		mPath = file.getAbsolutePath();
	}
}
