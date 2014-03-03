package com.stander;

import java.io.File;

public class FileItem {
	public static final String mBreak = "\n";
	public static final String mSpace = " ";
	public static final String mPeriod = ".";
	protected File mFile  = null;
	private String mName;
	
	protected void init(File file) {
		mFile = file;
	}
	
	public String getName() {
		return mName;
	}
	
	@Override
	public String toString() {
		StringBuilder mString = new StringBuilder();
		mString.append("Name : ").append(mName).append(mBreak);
		return mString.toString();
	}
}
