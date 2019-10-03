package org.weso.snoicd.core

import java.io.Serializable
import java.util.ArrayList

/**
 * A concept in snoicd represents an object with a unique code on a terminology/ontology,
 * a set of possible descriptions and the set of related codes.
 */
data class Concept(var code : String = "",
                   var terminologyName : String = "",
                   var descriptions : List<String> = ArrayList(),
                   var relatedCodes : List<SimpleConcept> = ArrayList()) : Serializable {

    companion object {
        private val serialVersionUID = 1L
    }
}
