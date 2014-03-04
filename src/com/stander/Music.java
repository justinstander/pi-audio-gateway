package com.stander;

import java.io.File;

/**
 * @author jstander
 *
 */
public class Music extends FileItem {
	private String mPath;

	/**
	 * @param file
	 * @param index
	 */
	public Music(File file,int index) {
		super();
		init(file,index);
	}
	
	/* (non-Javadoc)
	 * @see com.stander.FileItem#init(java.io.File, int)
	 */
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
}
