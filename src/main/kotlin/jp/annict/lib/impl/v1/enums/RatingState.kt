package jp.annict.lib.impl.v1.enums

enum class RatingState {

    BAD,
    AVERAGE,
    GOOD,
    GREAT;

    override fun toString() : String {
        return name.toLowerCase()
    }
}