/**
 * 
 */
package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author jstander
 *
 */
public final class Model {
	private static Model instance;
	private List<Artist> mArtists;
	private String mFileList;
	
	/**
	 * Private, Singleton
	 */
	private Model() {
		super();
	}
	
	/**
	 * @return Singlton instance
	 */
	public static Model getInstance() {
		if( instance == null ) {
			instance = new Model();
		}
		return instance;
	}
	
	/**
	 * @return
	 */
	public List<Artist> getArtists() {
		return mArtists;
	}
	
	/**
	 * @return
	 */
	public String getFileList() {
		return mFileList;
	}
	
	/**
	 * @return
	 */
	public void init(String path) {
		File root = new File(path);
		mArtists = new ArrayList<Artist>();
		
		if( root.exists() ) {
			File[] files = root.listFiles();
			int count = files.length;
			for(int i=0;i<count;i++) {
				File file = files[i];
				if( file.isDirectory() ) {
					int id = mArtists.size();
					mArtists.add(new Artist(files[i],id));
				}
			}
		}
		
		Gson gson = new GsonBuilder().create();
		mFileList = gson.toJson(mArtists);
	}
}
