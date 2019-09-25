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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO object to represent a concept in the context of the system. It is
 * composed by an id, its code, terminology name, its descriptions, and related
 * codes.
 * 
 * <p>
 * Will be printed as { code, terminologyName, description and relatedCodes } in
 * JSON.
 * </p>
 * 
 * @author Guillermo Facundo Colunga
 * @version since 1.0
 */
@Data
@JsonPropertyOrder({ "code", "terminologyName", "descriptions", "relatedCodes" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Concept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String terminologyName;
	private List<String> descriptions;
	private List<SimpleConcept> relatedCodes;

	/**
	 * Getter for the descriptions list of a concept. If there is no list will
	 * create, associate and return an empty one.
	 * 
	 * @return The lists of descriptions if exists or an empty one if it does
	 *         not exists one.
	 */
	public List<String> getDescriptions() {
		if (this.descriptions == null) {
			return this.descriptions = new ArrayList<String>();
		} else {
			return this.descriptions;
		}
	}

	/**
	 * Getter for the descriptions list of related codes. If there is no list will
	 * create, associate and return an empty one.
	 * 
	 * @return The lists of related codes if exists or an empty one if it does
	 *         not exists one.
	 */
	public List<SimpleConcept> getRelatedCodes() {
		if (this.relatedCodes == null) {
			return this.relatedCodes = new ArrayList<SimpleConcept>();
		} else {
			return this.relatedCodes;
		}
	}
}
