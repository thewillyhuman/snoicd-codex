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

import lombok.extern.slf4j.Slf4j;

/**
 * Instance of GetChildrenControllerImpl.java
 * 
 * @author 
 * @version 
 */
@Slf4j
@Controller
public class GetChildrenControllerImpl implements GetChildrenController {

	/* (non-Javadoc)
	 * @see org.weso.snoicd.controllers.GetChildrenController#getChildren(java.lang.String)
	 */
	@Override
	public ResponseEntity<String> getChildren( String term ) {
		Map<String, String> responseMap = new HashMap<String, String>();
		HttpStatus responseStatus = null;
		
		if (term == null || term.equals( "" )) {
			responseMap.put( "error", "no code detected" );
			responseStatus = HttpStatus.BAD_REQUEST;
			log.error( "Not possible to get the children of an empty term" );
		} else {
			responseMap.put( "code", term );
			responseMap.put( "children", "" /* service.getChildren(term) */ );
			responseStatus = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>( new JSONObject( responseMap ).toString(),
				responseStatus );
	}

}
