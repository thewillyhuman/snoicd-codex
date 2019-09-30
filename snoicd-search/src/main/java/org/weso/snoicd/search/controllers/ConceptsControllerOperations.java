package org.weso.snoicd.search.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.weso.snoicd.types.ResponseToQuery;

import javax.validation.constraints.NotNull;

@RequestMapping("/default")
public interface ConceptsControllerOperations {

    /**
     * Defines an entry point for the search of concepts in the system.
     *
     * @param q      is the query to search.
     * @param filter to apply if present.
     * @return a ResponseEntity object with a ResponseToQuery object in the body
     * containing the result of executing the given query.
     */
    @RequestMapping("/search")
    DeferredResult<ResponseEntity<ResponseToQuery>> searchEntryPoint(
            @RequestParam @NotNull String q,
            @RequestParam(required = false) @Nullable String filter
    );
}
