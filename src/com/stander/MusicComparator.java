package com.stander;

import java.util.Comparator;

/**
 * Compares songs (music) based on track number. The track number is read
 * through the MP3's ID3 tag.
 * 
 * @author justin.stander
 */
public class MusicComparator implements Comparator<Music> {

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Music arg0, Music arg1) {
		if( arg0.trackNumber < arg1.trackNumber ) {
			return -1;
		}
		if( arg0.trackNumber > arg1.trackNumber ) {
			return 1;
		}
		return 0;
	}

}
