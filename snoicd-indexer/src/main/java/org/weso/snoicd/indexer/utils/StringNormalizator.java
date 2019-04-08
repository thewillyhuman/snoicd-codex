/*
 * This source file is part of the snoicd-indexer open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-indexer project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.indexer.utils;

import java.text.Normalizer;

/**
 * Instance of StringNormalizator.java
 * 
 * @author 
 * @version 
 */
public class StringNormalizator {
	
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
