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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Controller
public class DecodeCodeControllerImpl {
	
	@Autowired
	TermsService service;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.weso.snoicd.controllers.DecodeController#decodeCode(org.weso.snoicd.
	 * Query)
	 */
	@RequestMapping(value = "/decode", method = RequestMethod.POST)
	public TermNode decodeCode(@RequestBody Map<String, Object> payload ) {
		// If there is no token then notify the user.
		if (payload == null || !payload.containsKey( "term" )) {
			log.error( "Not possible to decode empty term: " + payload );
			return new TermNode();
		} else {
			log.info( "Decoding term: " + payload.get( "term" ) );
			return service.decode(payload.get( "term" ).toString());
		}
		
	}

}
