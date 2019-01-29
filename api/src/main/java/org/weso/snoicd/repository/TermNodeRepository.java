/*******************************************************************************
 * MIT License
 * 
 * Copyright (c) 2019 CODE OWNERS (See CODE_OWNERS.TXT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
