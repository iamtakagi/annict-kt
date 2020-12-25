package jp.annict.enums

enum class RatingState {

    BAD,
    AVERAGE,
    GOOD,
    GREAT;

    override fun toString() : String {
        return name.toLowerCase()
    }
}