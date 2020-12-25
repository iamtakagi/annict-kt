package jp.annict.enums

enum class Media {

    TV,
    OVA,
    MOVIE,
    WEB,
    OTHER;

    override fun toString() : String {
        return name.toLowerCase()
    }



}