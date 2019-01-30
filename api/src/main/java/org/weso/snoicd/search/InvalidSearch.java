package org.weso.snoicd.search;

import org.springframework.http.HttpStatus;
import org.weso.snoicd.types.ResponseToQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidSearch implements Search {

	@Override
	public ResponseToQuery execute() {
		log.info("Filter was: invalid.");
		log.info("SEARCH invalidated.");
		
		ResponseToQuery rtq = new ResponseToQuery();
		rtq.setQuery("invalid-search");
		rtq.setResult(null);
		rtq.setStatus(HttpStatus.BAD_REQUEST);
		
		return rtq;
	}

}
