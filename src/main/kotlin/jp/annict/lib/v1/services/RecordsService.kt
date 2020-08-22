package jp.annict.lib.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.Record
import jp.annict.lib.v1.enums.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class RecordsGetRequestQuery (
    val fields           : Array<String>?=null,
    val filter_ids       : Array<Long>?=null,
    val filter_episode_id : Long?=null,
    val filter_has_record_comment: Boolean?=null,
    val page              : Long?=null,
    val per_page          : Long?=null,
    val sort_id           : Order?=null,
    val sort_likes_count  : Order?=null
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
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_likes_count != null) { addQueryParameter("sort_likes_count", sort_likes_count.name) }

        }.build()
    }
}

data class RecordsGetResponseData (
    val records     : Array<Record>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) : ResponseData<RecordsGetResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): RecordsGetResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return RecordsGetResponseData(
                    JsonUtil.GSON.fromJson(getAsJsonArray("records"), object : TypeToken<Array<Record>>() {}.type),
                    if (get("total_count").isJsonNull) null else get("total_count").asLong,
                    if (get("next_page").isJsonNull) null else get("next_page").asLong,
                    if (get("prev_page").isJsonNull) null else get("prev_page").asLong
                )
            }
        }
    }
}

class RecordsService(client: AnnictClient) : BaseService(client){

    fun get(query: RecordsGetRequestQuery) : RecordsGetResponseData {
         this.client.apply {
             return RecordsGetResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
         }
    }
}
