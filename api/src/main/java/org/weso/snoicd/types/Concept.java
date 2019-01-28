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
