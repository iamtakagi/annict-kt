package jp.annict.enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class RatingState {

    @SerialName("bad") BAD,
    @SerialName("average") AVERAGE,
    @SerialName("good") GOOD,
    @SerialName("great") GREAT;

    override fun toString() : String {
        return name.toLowerCase()
    }
}