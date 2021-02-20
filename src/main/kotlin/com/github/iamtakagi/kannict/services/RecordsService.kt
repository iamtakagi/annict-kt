package com.github.iamtakagi.kannict.services

import jp.annict.client.AnnictClient
import jp.annict.models.Record
import jp.annict.enums.Order
import jp.annict.exception.AnnictError
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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
)  {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("records")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_episode_id != null) { addQueryParameter("filter_episode_id", filter_episode_id.toString()) }
            if(filter_has_record_comment != null) { addQueryParameter("filter_has_record_comment", filter_has_record_comment.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }
            if(sort_likes_count != null) { addQueryParameter("sort_likes_count", sort_likes_count.toString()) }

        }.build()
    }
}

@Serializable
data class RecordsGetResponseData (
    val records     : Array<Record>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): RecordsGetResponseData? {
        response.apply {
            if(response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<RecordsGetResponseData>(it) }
        }
    }
}

class RecordsService(val client: AnnictClient) {

    internal fun get(query: RecordsGetRequestQuery) : RecordsGetResponseData? {
        this.client.apply {
            return RecordsGetResponseData()
                    .parse(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}
