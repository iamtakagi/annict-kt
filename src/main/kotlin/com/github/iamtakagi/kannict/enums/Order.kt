package com.github.iamtakagi.kannict.enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Order
 *
 * @constructor Create empty Order
 */
@Serializable
enum class Order {
    @SerialName("asc") ASC,
    @SerialName("desc") DESC;
}