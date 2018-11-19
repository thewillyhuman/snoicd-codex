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
 * Instance of ICD10Decoder.java
 * 
 * @author 
 * @version 
 */
public class ICD10Decoder extends Decoder {

	public ICD10Decoder(String termToDecode) {
		super(termToDecode);
	}

	/* (non-Javadoc)
	 * @see org.weso.snoicd.services.Decoder#decode()
	 */
	@Override
	public String decode() {
		// Call to the repository
		return super.termToDecode;
	}

}
