/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.weso.snoicd.types.TermNode;

/**
 * Instance of TermNodeRepository.java
 * 
 * @author 
 * @version 
 */
@Repository
public interface TermNodeRepository extends MongoRepository<TermNode, ObjectId> {
	
	/**
	 * Returns a term node from the knowledge graph by its snomed code.
	 * @param snomedCode
	 * @return
	 */
	TermNode findBySnomedCode(String snomedCode);
	
	/**
	 * 
	 * @param icd9Code
	 * @return
	 */
	TermNode findByIcd9Code(String icd9Code);
	
	/**
	 * 
	 * @param icd10Code
	 * @return
	 */
	List<TermNode> findByIcd10Code(String icd10Code);
	
	/**
	 * 
	 * @param description
	 * @return
	 */
	@Query("{'descriptions.description': {$regex: ?0, $options: 'i' }})")
	List<TermNode> findByDescription(String description);
	
}
