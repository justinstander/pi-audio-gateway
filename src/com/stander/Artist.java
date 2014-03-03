package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Artist extends FileItem {
	private List<Album> mAlbums = null;
	
	public Artist(File file) {
		super();
		init(file);
	}
	
	public List<Album> getAlbums() {
		return mAlbums;
	}
	
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder(super.toString());
		
		return mString.toString();
	}
	
	@Override
	protected void init(File file) {
		super.init(file);
		
		mAlbums = new ArrayList<Album>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++ ) {
			if(files[i].isDirectory()) {
				mAlbums.add(new Album(files[i]));
			}
		}
	}
}
