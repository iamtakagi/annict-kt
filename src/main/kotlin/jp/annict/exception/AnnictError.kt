package jp.annict.exception

data class AnnictError(val msg: String? = null) : Exception(msg)