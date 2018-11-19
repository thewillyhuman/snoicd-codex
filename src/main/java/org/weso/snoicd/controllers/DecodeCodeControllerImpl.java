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

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.weso.snoicd.knowledge.graph.TermNode;

import lombok.extern.slf4j.Slf4j;


/**
 * Instance of DecodeCodeController.java
 * 
 * @author
 * @version
 */
@Slf4j
@Controller
public class DecodeCodeControllerImpl implements DecodeController {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.weso.snoicd.controllers.DecodeController#decodeCode(org.weso.snoicd.
	 * Query)
	 */
	@Override
	public ResponseEntity<String> decodeCode( String term ) {
		Map<String, String> responseMap = new HashMap<String, String>();
		HttpStatus responseStatus = null;

		// If there is no token then notify the user.
		if (term == null || term.equals( "" )) {
			responseMap.put( "error", "no code detected" );
			responseStatus = HttpStatus.BAD_REQUEST;
			log.error( "Not possible to decode empty term" );
		} else {
			responseMap.put( "response", new TermNode().toString() /* service.decode(term) */ );
			responseStatus = HttpStatus.OK;
		}

		return new ResponseEntity<String>( new JSONObject( responseMap ).toString(),
				responseStatus );
	}

}
