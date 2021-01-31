package jp.annict.exception

/**
 * Annict error
 *
 * @property msg
 * @constructor Create empty Annict error
 */
data class AnnictError(val msg: String? = null) : Exception(msg)