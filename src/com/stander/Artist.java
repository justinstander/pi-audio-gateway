package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author justin.stander
 *
 */
public class Artist extends FileItem {
	private List<Album> mAlbums = null;
	
	/**
	 * @param file
	 */
	public Artist(File file,int index) {
		super();
		init(file,index);
	}
	
	/**
	 * @return
	 */
	public List<Album> getAlbums() {
		return mAlbums;
	}
	
	/**
	 * @see com.stander.FileItem#toString()
	 */
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder(super.toString()).append(String.format("%n"));
		int a = mAlbums.size();
		for( int i=0;i<a;i++ ) {
			mString.append(mAlbums.get(i).toString());
		}
		return mString.toString();
	}
	
	/**
	 * @see com.stander.FileItem#init(java.io.File)
	 */
	@Override
	protected void init(File file,int index) {
		super.init(file,index);
		
		mAlbums = new ArrayList<Album>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++ ) {
			if(files[i].isDirectory()) {
				int id = mAlbums.size();
				mAlbums.add(new Album(files[i],id,getId()));
			}
		}
	}
}
