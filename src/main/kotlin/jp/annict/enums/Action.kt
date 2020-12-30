package jp.annict.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Action {

    @SerialName("create_record") CREATE_RECORD,
    @SerialName("create_review") CREATE_REVIEW,
    @SerialName("create_multiple_records") CREATE_MULTIPLE_RECORDS,
    @SerialName("create_status") CREATE_STATUS;

    override fun toString() : String {
        return name.toLowerCase()
    }
}