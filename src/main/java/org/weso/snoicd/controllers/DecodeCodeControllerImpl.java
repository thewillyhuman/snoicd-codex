/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.weso.snoicd.knowledge.graph.TermNode;
import org.weso.snoicd.services.TermsService;

import lombok.extern.slf4j.Slf4j;


/**
 * Instance of DecodeCodeController.java
 * 
 * @author
 * @version
 */
@Slf4j
@RestController
public class DecodeCodeControllerImpl {
	
	@Autowired
	TermsService service;


	/*
	 * (non-Javadoc)
	 * @see
	 * org.weso.snoicd.controllers.DecodeController#decodeCode(org.weso.snoicd.
	 * Query)
	 */
	@RequestMapping(value = "/api/codes/{codeId}", method = RequestMethod.GET)
	public TermNode decodeCodeS( @PathVariable @NotNull String codeId ) {
		
		// If there is no term or the term is empty...
		if (codeId == null || codeId.isEmpty()) {
			log.error( "Payload not found" );
			return null;
		} 
		
		log.info( "Decoding term: " + codeId );
		return service.decode( codeId );
	}

}
