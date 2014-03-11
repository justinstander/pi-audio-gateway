package com.stander;

import java.io.File;

/**
 * @author justin.stander
 *
 */
public class FileItem {
	protected static final String LINE_BREAK = "%n";
	private static final Object NAME = " Name : ";
	private String mName;
	private int mId;
	protected File mFile  = null;
	
	/**
	 * @param file
	 */
	protected void init(File file,int index) {
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
		mString.append(mId).append(NAME).append(mName);
		return mString.toString();
	}
}
