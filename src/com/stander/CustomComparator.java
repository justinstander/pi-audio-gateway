package com.stander;

import java.util.Comparator;

public class CustomComparator implements Comparator<Music> {

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
