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
import org.weso.snoicd.queries.Query;

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
	public ResponseEntity<String> decodeCode( Query query ) {
		Map<String, String> responseMap = new HashMap<String, String>();
		HttpStatus responseStatus = null;

		// If there is no security token then notify the user.
		if (query.getSecurityToken() == null /* securityManager.auth(query.tocken)*/) {
			responseMap.put( "error", "security token not valid" );
			responseStatus = HttpStatus.BAD_REQUEST;
			log.debug( "Error wile loading " );
		} else {
			responseMap.put( "code", query.getCode() );
			responseMap.put( "description", "" /* service.decode(query) */ );
			responseStatus = HttpStatus.OK;
		}

		return new ResponseEntity<String>( new JSONObject( responseMap ).toString(),
				responseStatus );
	}

}
