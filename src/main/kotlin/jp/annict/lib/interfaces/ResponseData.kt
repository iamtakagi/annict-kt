package jp.annict.lib.interfaces

import okhttp3.Response

/**
 * Response を data class として提供させる
 */
interface ResponseData<T> {
    fun toDataClass(response: Response) : T
}