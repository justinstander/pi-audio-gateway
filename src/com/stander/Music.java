package com.stander;

import java.io.File;

/**
 * @author jstander
 *
 */
public class Music extends FileItem {
	private int mId;
	private String mPath;

	public Music(File file,int index) {
		super();
		init(file,index);
	}
	
	@Override
	protected void init(File file,int index) {
		super.init(file,index);
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
