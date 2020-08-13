package jp.annict.rest.v1.type

enum class Status(val value: String) {

    WANNA_WATCH("wanna_watch"),
    WATCHING("watching"),
    WATCHED("watched"),
    ON_HOLD("on_hold"),
    STOP_WATCHING("stop_watching"),
    NO_SELECT("no_select"),
}