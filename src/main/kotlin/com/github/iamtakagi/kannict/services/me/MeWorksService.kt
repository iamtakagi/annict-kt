package com.github.iamtakagi.kannict.services.me

import com.github.iamtakagi.kannict.client.AnnictClient
import com.github.iamtakagi.kannict.enums.Order
import com.github.iamtakagi.kannict.enums.Status
import com.github.iamtakagi.kannict.exception.AnnictError
import com.github.iamtakagi.kannict.models.MeWork
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeWorksGetRequestQuery (
    val fields           : Array<String>?=null,
    val filter_ids       : Array<Long>?=null,
    val filter_season       : String?=null,
    val filter_title        : String?=null,
    val page                : Long?=null,
    val per_page            : Long?=null,
    val sort_id             : Order?=null,
    val sort_season         : Order?=null,
    val sort_watchers_count : Order?=null,
    val filter_status: Status?=null
) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/works")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_season != null && filter_season.isNotEmpty()) { addQueryParameter("filter_season", filter_season.toString()) }
            if(filter_title != null && filter_title.isNotEmpty()) { addQueryParameter("filter_title", filter_title.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }
            if(sort_season != null) { addQueryParameter("sort_season", sort_season.toString()) }
            if(sort_watchers_count != null) { addQueryParameter("sort_watchers_count", sort_watchers_count.toString()) }
            if(filter_status != null) { addQueryParameter("filter_status", filter_status.toString()) }

        }.build()
    }
}

@Serializable
data class MeWorksGetResponseData (
    val works: Array<MeWork>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): MeWorksGetResponseData? {
         response.apply {
             if (response.code != 200) {
                 return throw AnnictError(response.message)
             }
             return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeWorksGetResponseData>(it) }
         }
    }

}

class MeWorksService(val client: AnnictClient)  {

     internal fun get(query: MeWorksGetRequestQuery) : MeWorksGetResponseData? {
        this.client.apply {
            return MeWorksGetResponseData()
                .parse(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}