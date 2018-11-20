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

import org.weso.snoicd.knowledge.graph.TermNode;

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
	public TermNode decodeCode(Map<String, Object> payload);
}
