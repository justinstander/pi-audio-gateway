/**
 * 
 */
package com.stander;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
		
		int count;
		if( root.exists() ) {
			File[] files = root.listFiles();
			
			count = files.length;
			for(int i=0;i<count;i++) {
				File file = files[i];
				if( file.isDirectory() ) {
					mArtists.add(new Artist(files[i]));
				}
			}
			
			Collections.sort(mArtists,new AlphabeticComparator());
			
			int finalCount = mArtists.size();
			for(int j=0;j<finalCount;j++) {
				mArtists.get(j).setId(j);
			}
		}
		
		Gson gson = new GsonBuilder().create();
		mFileList = gson.toJson(mArtists);
	}
}
