package jp.annict.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Status {

    @SerialName("wanna_watch") WANNA_WATCH,
    @SerialName("watching") WATCHING,
    @SerialName("watched") WATCHED,
    @SerialName("on_hold") ON_HOLD,
    @SerialName("stop_watching") STOP_WATCHING,
    @SerialName("no_select") NO_SELECT;

    override fun toString() : String {
        return name.toLowerCase()
    }
}