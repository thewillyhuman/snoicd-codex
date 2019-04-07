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
package org.weso.snoicd.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.weso.snoicd.search.SearchExecutor;
import org.weso.snoicd.services.ConceptsService;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ConceptsController {

	ConceptsService service = new ConceptsService();
	
	// The response to the executed query.
	private ResponseToQuery rtq;

	/**
	 * Defines an entry point for the search of concepts in the system.
	 * 
	 * @param q is the query to search.
	 * @param filter to apply if present.
	 * @return a ResponseEntity object with a ResponseToQuery object in the body
	 *         containing the result of executing the given query.
	 */
	@RequestMapping(value = "/api/search", method = RequestMethod.GET)
	public ResponseEntity<ResponseToQuery> searchEntryPoint(@RequestParam @NotNull String q,
			@RequestParam(required = false) @Nullable String filter) {

		log.info("SEARCH request received.");
		System.err.println( q );
		rtq = new SearchExecutor(q, filter).execute(this.service);
		
		return new ResponseEntity<ResponseToQuery>(rtq, rtq.getStatus());
	}
}
