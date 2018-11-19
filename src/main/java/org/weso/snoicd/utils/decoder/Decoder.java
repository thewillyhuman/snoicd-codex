/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.utils.decoder;

/**
 * Instance of Decoder.java
 * 
 * @author 
 * @version 
 */
public abstract class Decoder {
	
	protected String termToDecode;
	
	public Decoder(String termToDecode) {
		this.termToDecode = termToDecode;
	}

	public abstract String decode();
}
