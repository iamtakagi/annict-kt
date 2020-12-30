package jp.annict.enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class Order {
    @SerialName("asc") ASC,
    @SerialName("desc") DESC;

    override fun toString() : String {
        return name.toLowerCase()
    }
}