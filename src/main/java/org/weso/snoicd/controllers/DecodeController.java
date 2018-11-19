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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Instance of DecodeController.java
 * 
 * @author 
 * @version 
 */
public interface DecodeController {
	
	/**
	 * Decodes an ICD or SNOMED CT code and returns its description. 
	 * 
	 * @return the description of the code provided.
	 */
	@RequestMapping(value = "/decode", method = RequestMethod.GET)
	public ResponseEntity<String> decodeCode(@RequestBody String term);
}
