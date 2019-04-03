package org.weso.snoicd.utils;

import java.text.Normalizer;

public class StringNormalizatior {

	public static String normalize(String s) {
		
		// Removing '-' and replacing it by a white space.
		String ret = s.replaceAll("-", " ");
		
		// Normalizing special characters to simple ascii ones.
		ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		
		// Always to lower case as a convention.
		ret = ret.toLowerCase();
		
		return ret;
	}
}
