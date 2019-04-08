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
package org.weso.snoicd.search.services;

import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;
import org.weso.snoicd.search.persistence.Persistence;
import org.weso.snoicd.search.persistence.PersistenceImpl;
import org.weso.snoicd.search.types.Concept;

@EntityScan
@Service
public class ConceptsService {
	
	Persistence repository = PersistenceImpl.instance;

	/**
	 * Gets the concept code.
	 * @param code to be look for.
	 * @return the list of concepts that match the given code.
	 */
	public Set<Concept> getConceptByCode(String code) {
		return this.repository.findByCode(code);
	}
	
	/**
	 * Gets the concept code.
	 * @param code to be look for.
	 * @return the list of concepts that match the given code.
	 */
	public Set<Concept> getConceptByDescription(String description) {
		String[] words = description.split( " " );
		return this.repository.findByDescription( words );
	}
	
	/**
	 * Gets the concepts to search for.
	 * 
	 * @param query to be executed.
	 * @return the list of concepts.
	 */
	public Set<Concept> getConceptsSearch(String query) {
		return this.repository.search(query);
	}
	
}
