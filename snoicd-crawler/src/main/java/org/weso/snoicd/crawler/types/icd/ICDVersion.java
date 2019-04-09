/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.types.icd;

/**
 * Instance of ICDVersion.java
 * 
 * @author 
 * @version 
 */
public enum ICDVersion {
	V_9 		(9),
	V_10 	(10)
	;
	
	private final int version;
	
	ICDVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return this.version;
	}
}
