package jp.annict.rest.interfaces

import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Response を data class として提供させる
 */
interface ResponseData<T> {
    fun toDataClass(response: Response) : T
}