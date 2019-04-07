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
package org.weso.snoicd.types;

import java.util.Set;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Represents a response to a given query in the context of the system. A query
 * consists on a String that represents the text to search, the response status
 * and the list containing all the results after the query execution.
 * 
 * @author Guillermo Facundo Colunga
 * @version since 1.0
 */
@Data
public class ResponseToQuery {

	// The status received after executing the query.
	private HttpStatus status;

	// The original query.
	private String query;

	// The lists of results.
	private Set<Concept> result;

}
