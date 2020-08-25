package jp.annict.lib.impl.v1.enums

enum class Order {
    ASC, DESC;

    override fun toString() : String {
        return name.toLowerCase()
    }
}