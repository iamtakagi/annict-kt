package jp.annict.lib.impl.v1.enums

enum class Action {

    CREATE_RECORD,
    CREATE_REVIEW,
    CREATE_MULTIPLE_RECORDS;

    override fun toString() : String {
        return name.toLowerCase()
    }
}