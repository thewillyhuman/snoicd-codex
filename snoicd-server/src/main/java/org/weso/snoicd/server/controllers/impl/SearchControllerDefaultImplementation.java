package org.weso.snoicd.server.controllers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.weso.snoicd.core.ResponseToQuery;
import org.weso.snoicd.search.SnoicdSearch;
import org.weso.snoicd.server.services.SearchExecutorService;
import org.weso.snoicd.server.controllers.SearchControllerOperations;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/api")
public class SearchControllerDefaultImplementation implements SearchControllerOperations {

    SnoicdSearch search;

    @Override
    @GetMapping("/search")
    public DeferredResult<ResponseEntity<ResponseToQuery>> handleSearchQuery(
            @NotNull @RequestParam(name = "q") String query,
            @Nullable @RequestParam(required = false, name = "filter") String filter) {

        DeferredResult<ResponseEntity<ResponseToQuery>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(
                () -> {
                    output.setResult(
                            new ResponseEntity<ResponseToQuery>(
                                new SearchExecutorService(filter).execute(query),
                                HttpStatus.OK));
                }
        );

        return output;
    }
}
