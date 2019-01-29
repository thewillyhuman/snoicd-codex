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

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "code", "terminologyName", "descriptions", "relatedCodes" })
@Document( collection = "concepts" )
public class Concept {

	@Id
	@JsonIgnore
	private ObjectId _id;
	
	@Indexed
	private String code;
	
	@JsonProperty("terminology_name")
	private String terminologyName;
	
	@TextIndexed
	private List<String> descriptions;
	
	@JsonProperty("related_codes")
	private List<SimpleConcept> relatedCodes;
	
	public List<String> getDescriptions() {
		if(this.descriptions == null) {
			return this.descriptions = new ArrayList<String>();
		} else {
			return this.descriptions;
		}
	}
	
	public List<SimpleConcept> getRelatedCodes() {
		if(this.relatedCodes == null) {
			return this.relatedCodes = new ArrayList<SimpleConcept>();
		} else {
			return this.relatedCodes;
		}
	}
}
