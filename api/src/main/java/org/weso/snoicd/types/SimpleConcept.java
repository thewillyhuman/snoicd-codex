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

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * POJO object to represent a simple concept in the context of the system. It is
 * composed by an id, its code and terminology name.
 * 
 * @author Guillermo Facundo Colunga
 * @version since 1.0
 */
@Data
public class SimpleConcept {

	// The terminology code.
	private String code;

	// The lists of descriptions.
	private List<String> descriptions;

	// The terminology name.
	private String terminologyName;

	/**
	 * Getter for the descriptions of the simple concept. Will return the list
	 * of descriptions if exists or an empty one in other case.
	 * 
	 * @return the list of descriptions if exists or an empty one in other case.
	 */
	public List<String> getDescriptions() {
		if (this.descriptions == null) {
			return this.descriptions = new ArrayList<String>();
		} else {
			return this.descriptions;
		}
	}

}
