package jp.annict.rest.bases

import com.google.gson.reflect.TypeToken
import jp.annict.rest.utils.JsonUtil
import okhttp3.Response

/**
 * Response を data class として提供させる
 */
abstract class BaseResponseData<T> {

    fun toDataClass(response: Response) : T {
        response.apply {
            return JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject, object : TypeToken<T>() {}.type)
        }
    }
}