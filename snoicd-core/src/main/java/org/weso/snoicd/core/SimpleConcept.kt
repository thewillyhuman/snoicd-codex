package org.weso.snoicd.core

import java.io.Serializable
import java.util.ArrayList

/**
 * A simple-concepts in icd, as a Concept is a unique code in a terminology name
 * and the set of possible descriptions. But in this case there are no related
 * concepts, it is a node with no outbound edge.
 */
data class SimpleConcept(var code: String,
                         var descriptions: List<String> = ArrayList(),
                         var terminologyName: String) : Serializable {

    companion object {

        private val serialVersionUID = 1L
    }
}
