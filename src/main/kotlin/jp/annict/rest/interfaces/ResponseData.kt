package jp.annict.rest.interfaces

import com.google.gson.reflect.TypeToken
import jp.annict.rest.utils.JsonUtil
import okhttp3.Response

/**
 * Response を data class として提供させる
 */
interface ResponseData<T> {
    fun toDataClass(response: Response) : T
}