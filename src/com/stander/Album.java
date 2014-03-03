package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Album extends FileItem {
	private static final String MP3 = "mp3";
//	private static Logger logger = LoggerFactory.getLogger(Album.class);
	private List<Music> mMusic = null;
	
	public Album(File file) {
		super();
		init(file);
	}
	
	public List<Music> getMusic() {
		return mMusic;
	}
	
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder(super.toString());
		
		mString.append("---------------").append(mBreak);
		int a = mMusic.size();
		for(int i=0;i<a;i++) {
			mString.append(mSpace).append(a+1).append(mPeriod).append(mSpace).append(mMusic.get(a).toString()).append(mBreak);
		}
		mString.append("");
		return mString.toString();
	}
	
	@Override
	protected void init(File file) {
		super.init(file);
		
		mMusic = new ArrayList<Music>();
		
		File[] files = file.listFiles();
		int count = files.length;
		for( int i=0;i<count;i++) {
			File item = files[i];
			if( item.isFile() ) {
				String name = item.getName();
				int length = name.length();
				//logger.info("name: "+name);
				if( name.substring(length-3) == MP3 ) {
					mMusic.add(new Music(item));
				}
			}
		}
	}
}
