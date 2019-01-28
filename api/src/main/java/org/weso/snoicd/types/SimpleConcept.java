package org.weso.snoicd.types;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SimpleConcept {
	
	private String code;
	
	private List<String> descriptions;
	
	@JsonProperty("terminology_name")
	private String terminologyName;
	
	public List<String> getDescriptions() {
		if(this.descriptions == null) {
			return this.descriptions = new ArrayList<String>();
		} else {
			return this.descriptions;
		}
	}

}
