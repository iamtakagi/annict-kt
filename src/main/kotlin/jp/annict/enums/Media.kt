package jp.annict.enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class Media {

    @SerialName("tv") TV,
    @SerialName("ova") OVA,
    @SerialName("movie") MOVIE,
    @SerialName("web") WEB,
    @SerialName("other") OTHER;



}