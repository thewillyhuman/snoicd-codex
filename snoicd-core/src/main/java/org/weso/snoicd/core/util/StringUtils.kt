package org.weso.snoicd.core.util

import java.text.Normalizer

fun String.normalize() : String {
    var ret = this.replace("-".toRegex(), " ")
    ret = ret.replace(("/").toRegex(), " ")
    ret = ret.replace(("[.,]").toRegex(), "")
    ret = ret.replace(("[()]").toRegex(), "")
    ret = ret.replace(("[\\[\\]]").toRegex(), "")
    ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")
    ret = ret.toLowerCase();
    return ret
}
