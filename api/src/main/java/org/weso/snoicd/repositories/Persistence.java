/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.repositories;

import java.util.List;

import org.weso.snoicd.types.Concept;

/**
 * Instance of Persistence.java
 * 
 * @author 
 * @version 
 */
public interface Persistence {
	
	public List<Concept> findByCode(String code);
	
	public List<Concept> findByDescription(String... words);
	
	public List<Concept> search(String query);
	
	public void saveConcept(Concept concept);
	
	public void deleteAll();

}
