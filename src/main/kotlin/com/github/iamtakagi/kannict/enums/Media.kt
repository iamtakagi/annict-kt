package com.github.iamtakagi.kannict.enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Media
 *
 * @constructor Create empty Media
 */
@Serializable
enum class Media {

    @SerialName("tv") TV,
    @SerialName("ova") OVA,
    @SerialName("movie") MOVIE,
    @SerialName("web") WEB,
    @SerialName("other") OTHER;



}