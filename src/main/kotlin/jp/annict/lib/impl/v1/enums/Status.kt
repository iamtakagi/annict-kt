package jp.annict.lib.impl.v1.enums

enum class Status {


    WANNA_WATCH,
    WATCHING,
    WATCHED,
    ON_HOLD,
    STOP_WATCHING,
    NO_SELECT;

    override fun toString() : String {
        return name.toLowerCase()
    }
}