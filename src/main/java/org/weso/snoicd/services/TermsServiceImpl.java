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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weso.snoicd.knowledge.graph.TermNode;
import org.weso.snoicd.repository.TermNodeRepository;

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
	
	String icd10 = "([A-TV-Z][0-9][A-Z0-9](\\.?[A-Z0-9]{0,4})?)";
	String icd9 = "([V\\d]\\d{2}(\\.?\\d{0,2})?|E\\d{3}(\\.?\\d)?|\\d{2}(\\.?\\d{0,2})?)";

	@Autowired
	TermNodeRepository repository;

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.services.TermsService#decode(java.lang.String)
	 */
	@Override
	public TermNode decode( String term ) {
		
		if (term.matches( icd9 )) {
			log.info( "Decoding icd9 expression: " + term );
			// Call to the service corresponding method.
			return repository.findByIcd9Code( term );
		} else if (term.matches( icd10 )) {
			log.info( "Decoding icd10 expression: " + term );
			// Call to the service corresponding method.
			return repository.findByIcd10Code( term );
		} else {
			// If we r here I interpret it is mongo.
			return repository.findBySnomedCode( term );
		}
	}
}
