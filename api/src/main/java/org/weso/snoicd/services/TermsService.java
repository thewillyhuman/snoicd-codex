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

import java.util.List;

import org.weso.snoicd.types.TermNode;

/**
 * Instance of TermsService.java
 * 
 * @author 
 * @version 
 */
public interface TermsService {

	/**
	 * 
	 * @param term
	 * @return
	 */
	public TermNode decode(String term);
	
	/**
	 * 
	 * @param term
	 * @return
	 */
	public List<TermNode> getTermForDescription(String term);
}
