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
package org.weso.snoicd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;
import org.weso.snoicd.repositories.ConceptsRepository;
import org.weso.snoicd.types.Concept;

@EntityScan
@Service
public class ConceptsService {
	
	@Autowired
	ConceptsRepository repository;

	/**
	 * Gets the concept code.
	 * @param code to be look for.
	 * @return the list of concepts that match the given code.
	 */
	public List<Concept> getConceptByCode(String code) {
		return this.repository.findByCode(code);
	}
	
	/**
	 * Gets the concepts to search for.
	 * 
	 * @param query to be executed.
	 * @return the list of words formatted as \"word1\" \"word2\"
	 */
	public List<Concept> getConceptsSearch(String query) {
		String[] terms = query.split("\\ ");
		StringBuilder q = new StringBuilder();

		for(String s : terms) {
			q.append( "\"" );
			q.append(s);
			q.append( "\"" );
			q.append( " " );
		} 
		
		return this.repository.search(q.toString());
	}
	
}
