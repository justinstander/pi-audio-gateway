package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author justin.stander
 *
 */
public class Album extends FileItem {
	private static final String MP3 = "mp3";
	private List<Music> mMusic = null;
	
	/**
	 * @param file
	 */
	public Album(File file,int index) {
		super();
		init(file,index);
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
	@Override
	protected void init(File file,int index) {
		super.init(file,index);
		
		mMusic = new ArrayList<Music>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++) {
			File item = files[i];
			if( item.isFile() ) {
				String name = item.getName();
				String extension = name.substring(name.lastIndexOf('.')+1);
				if( extension.equals(MP3) ) {
					int id = mMusic.size();
					mMusic.add(new Music(item,id));
				}
			}
		}
	}
}
