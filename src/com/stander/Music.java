package com.stander;

import java.io.File;

/**
 * @author jstander
 *
 */
public class Music {
	private int mId;
	private String mPath;

	public Music(File file) {
		super();
		init(file);
	}
	
	private void init(File file) {
		mPath = file.getAbsolutePath();
	}

	/**
	 * @return
	 */
	public String getPath() {
		return mPath;
	}
	
	public int getId() {
		return mId;
	}
}
