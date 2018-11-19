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
public class ICD9Decoder extends Decoder {
	
	public ICD9Decoder(String termToDecode) {
		super(termToDecode);
	}
	
	public String decode() {
		// Call to the repository.
		return super.termToDecode;
	}
}
