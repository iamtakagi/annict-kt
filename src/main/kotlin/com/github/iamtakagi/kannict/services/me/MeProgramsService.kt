package com.github.iamtakagi.kannict.services.me

import jp.annict.client.AnnictClient
import jp.annict.enums.Order
import jp.annict.exception.AnnictError
import jp.annict.models.Program
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

/**
 * Me programs get request query
 *
 * @property fields
 * @property filter_ids
 * @property filter_channel_ids
 * @property filter_work_ids
 * @property filter_started_at_gt
 * @property filter_started_at_lt
 * @property filter_unwatched
 * @property filter_rebroadcast
 * @property page
 * @property per_page
 * @property sort_id
 * @property sort_started_at
 * @constructor Create empty Me programs get request query
 */
class MeProgramsGetRequestQuery (
    val fields: Array<String>? =null,
    val filter_ids: Array<Long>? =null,
    val filter_channel_ids: Array<Long>? =null,
    val filter_work_ids: Array<Long>? =null,
    val filter_started_at_gt: String? =null,
    val filter_started_at_lt: String? =null,
    val filter_unwatched: Boolean? =null,
    val filter_rebroadcast: Boolean? =null,
    val page: Long? =null,
    val per_page: Long? =null,
    val sort_id: Order? =null,
    val sort_started_at: Order? =null
    ) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {

            addPathSegments("/me/programs")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_channel_ids != null && filter_channel_ids.isNotEmpty()) { addQueryParameter("filter_channel_ids", filter_channel_ids.joinToString(separator = ",")) }
            if(filter_work_ids != null && filter_work_ids.isNotEmpty()) { addQueryParameter("filter_work_ids", filter_work_ids.joinToString(separator = ",")) }
            if(filter_started_at_gt != null && filter_started_at_gt.isNotEmpty()) { addQueryParameter("filter_started_at_gt", filter_started_at_gt) }
            if(filter_started_at_lt != null && filter_started_at_lt.isNotEmpty()) { addQueryParameter("filter_started_at_lt", filter_started_at_lt) }
            if(filter_unwatched != null) { addQueryParameter("filter_unwatched", filter_unwatched.toString()) }
            if(filter_rebroadcast != null) { addQueryParameter("filter_rebroadcast", filter_rebroadcast.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }
            if(sort_started_at != null) { addQueryParameter("sort_started_at", sort_started_at.toString()) }

        }.build()
    }
}

@Serializable
data class MeProgramsGetResponseData (
    val programs: Array<Program>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): MeProgramsGetResponseData? {
        response.apply {
            if(response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeProgramsGetResponseData>(it) }
        }
    }
}

class MeProgramsService (val client: AnnictClient) {

    internal fun get(query: MeProgramsGetRequestQuery) : MeProgramsGetResponseData? {
        this.client.apply { return MeProgramsGetResponseData()
            .parse(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}