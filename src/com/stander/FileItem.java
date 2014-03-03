package com.stander;

import java.io.File;

/**
 * @author justin.stander
 *
 */
public class FileItem {
	
	private String mName;
	private int mId;
	protected File mFile  = null;
	
	/**
	 * @param file
	 */
	protected void init(File file,int index) {
		mFile = file;
		mName = file.getName();
		mId = index;
	}
	
	/**
	 * @return
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder();
		mString.append(mId).append(" Name : ").append(mName);
		return mString.toString();
	}
}
