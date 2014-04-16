package com.stander;

import java.util.Comparator;

/**
 * Compares the names of two file items, which will be folders in the cases
 * of Artists and Albums.
 * 
 * @author justin.stander
 */
public class AlphabeticComparator implements Comparator<FileItem> {
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(FileItem file0, FileItem file1) {
		int result = file0.getName().toLowerCase().compareTo(file1.getName().toLowerCase());
		
		return result;
	}
}
