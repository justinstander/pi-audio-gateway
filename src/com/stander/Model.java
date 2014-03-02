/**
 * 
 */
package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jstander
 *
 */
public final class Model {
	private static final String PATH = "/Users/jstander/music/jstander";
	//private static final String PATH = "/home/pi/music";
	private static Model instance;
	private List<File> mArtists;
	
	
	/**
	 * Private, Singleton
	 */
	private Model(List<File> artists) {
		super();
		mArtists = artists;
	}
	
	/**
	 * @return Singlton instance
	 */
	public static Model getInstance() {
		if( instance == null ) {
			instance = getModel();
		}
		return instance;
	}
	
	/**
	 * @return
	 */
	public List<File> getArtists() {
		return mArtists;
	}
	
	/**
	 * @return
	 */
	private static Model getModel() {
		File root = new File(PATH);
		File[] files = root.listFiles();
		List<File> directories = new ArrayList<File>();
		
		int count = files.length;
		for(int i=0;i<count;i++) {
			File file = files[i];
			if( file.isDirectory() ) {
				directories.add(files[i]);
			}
		}
		
		Model model = new Model(directories);
		return model;
	}
}
