package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseBody
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.AnnictClient
import jp.annict.rest.v1.type.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response
import java.util.*

data class User(
    val id            : Int,
    val username      : String,
    val name          : String,
    val description   : String,
    val url           : String,
    val records_count : Int,
    val created_at    : Date
)

data class Record(
    val id             : Int,
    val comment        : String,
    val rating         : Int,
    val is_modified    : Boolean,
    val likes_count    : Int,
    val comments_count : Int,
    val created_at     : Date,
    val user           : User,
    val work           : Work,
    val episode        : Episode
)

data class RecordsRequestQuery (
    val fields           : Array<String>?,
    val filter_ids       : Array<Int>?,
    val filter_episode_id : Int?,
    val filter_has_record_comment: Boolean?,
    val page              : Int?,
    val per_page          : Int?,
    val sort_id           : Order?,
    val sort_likes_count  : Order?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("records")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_episode_id != null) { addQueryParameter("filter_episode_id", filter_episode_id.toString()) }
            if(filter_has_record_comment != null) { addQueryParameter("filter_has_record_comment", filter_has_record_comment.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.value) }
            if(sort_likes_count != null) { addQueryParameter("sort_likes_count", sort_likes_count.value) }

        }.build()
    }
}

data class RecordsResponse (
    val records     : Array<Record>?
) : ResponseBody<RecordsResponse> {

    constructor() : this(null)

    override fun toDataClass(response: Response): RecordsResponse {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return RecordsResponse(
                    JsonUtil.GSON.fromJson(getAsJsonArray("records"), object : TypeToken<Array<Record>>() {}.type)
                )
            }
        }
    }
}

class RecordsService(val client: AnnictClient) {

    fun get(query: RecordsRequestQuery) : RecordsResponse {
         this.client.apply {
             return RecordsResponse().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
         }
    }
}
