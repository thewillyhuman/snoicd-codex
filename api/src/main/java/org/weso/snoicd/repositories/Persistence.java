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

import java.util.Set;

import org.weso.snoicd.types.Concept;

/**
 * Instance of Persistence.java
 * 
 * @author 
 * @version 
 */
public interface Persistence {
	
	public Set<Concept> findByCode(String code);
	
	public Set<Concept> findByDescription(String... words);
	
	public Set<Concept> search(String query);
	
	public void saveConcept(Concept concept);
	
	public void deleteAll();

}
