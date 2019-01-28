package org.weso.snoicd.types;

import java.util.List;

import lombok.Data;

@Data
public class ResponseToQuery {
	
	// The status received after executing the query.
	private String status;
	
	// The original query.
	private String query;
	
	private List<Concept> result;

}
