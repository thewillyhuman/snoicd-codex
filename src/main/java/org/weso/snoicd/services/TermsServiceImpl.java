/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.weso.snoicd.utils.decoder.ICD10Decoder;
import org.weso.snoicd.utils.decoder.ICD9Decoder;

import lombok.extern.slf4j.Slf4j;

/**
 * Instance of TermsServiceImpl.java
 * 
 * @author
 * @version
 */
@Slf4j
@Service
public class TermsServiceImpl implements TermsService {

	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	InputStream stream = loader.getResourceAsStream( "/expressions.properties" );
	Properties regularExpressions = new Properties();

	public TermsServiceImpl() {
		try {
			regularExpressions.load( stream );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error( e.getMessage() );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.services.TermsService#decode(java.lang.String)
	 */
	@Override
	public String decode( String term ) {
		
		if (term.matches( regularExpressions.getProperty( "icd9" ) )) {
			log.info( "Decoding icd9 expression: " + term );
			return new ICD9Decoder( term ).decode();
		} else if (term.matches( regularExpressions.getProperty( "icd9" ) )) {
			log.info( "Decoding icd10 expression: " + term );
			return new ICD10Decoder(term).decode();
		}
			
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.services.TermsService#getChildren(java.lang.String)
	 */
	@Override
	public List<String> getChildren( String term ) {
		// TODO Auto-generated method stub
		return null;
	}

}
