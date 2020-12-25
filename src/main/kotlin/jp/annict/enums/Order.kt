package jp.annict.enums

enum class Order {
    ASC, DESC;

    override fun toString() : String {
        return name.toLowerCase()
    }
}