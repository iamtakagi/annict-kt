package jp.annict.services

import jp.annict.client.AnnictClient
import jp.annict.enums.Order
import jp.annict.exception.AnnictError
import jp.annict.models.Person
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class PeopleGetRequestQuery (
    val fields           : Array<String>?=null,
    val filter_ids       : Array<Long>?=null,
    val filter_work_id   : Long?=null,
    val page             : Long?=null,
    val per_page         : Long?=null,
    val sort_id          : Order?=null) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("people")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_work_id != null) { addQueryParameter("filter_work_id", filter_work_id.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }

        }.build()
    }
}

@Serializable
data class PeopleGetResponseData (
    val people: Array<Person>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): PeopleGetResponseData? {
        response.apply {
            if(response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<PeopleGetResponseData>(it) }
        }
    }
}

class PeopleService(val client: AnnictClient) {

    internal fun get(query: PeopleGetRequestQuery) : PeopleGetResponseData? {
        this.client.apply {
            return PeopleGetResponseData()
                .parse(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }

}