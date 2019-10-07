package org.weso.snoicd.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.weso.snoicd.core.Concept;
import org.weso.snoicd.core.ResponseToQuery;

import java.util.Set;

@RequestMapping("/default")
public interface SearchControllerOperations {

    /**
     *
     * @return
     */
    DeferredResult<ResponseEntity<ResponseToQuery>> handleSearchQuery(
            String query,
            String filter);
}
