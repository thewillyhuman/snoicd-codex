package org.weso.snoicd.core

import java.io.Serializable

data class ResponseToQuery(var query: String,
                           var result: Set<Concept>) : Serializable
