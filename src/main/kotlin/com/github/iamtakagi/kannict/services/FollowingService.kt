package com.github.iamtakagi.kannict.services

import com.github.iamtakagi.kannict.models.User
import com.github.iamtakagi.kannict.client.AnnictClient
import com.github.iamtakagi.kannict.enums.Order
import com.github.iamtakagi.kannict.exception.AnnictError
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class FollowingGetRequestQuery (
    val fields           : Array<String>?=null,
    val filter_user_id       : Long?=null,
    val filter_username   : String?=null,
    val page             : Long?=null,
    val per_page         : Long?=null,
    val sort_id          : Order?=null
) {

    internal fun url(builder: HttpUrl.Builder) : HttpUrl {
        return builder.apply {
            addPathSegment("following")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_user_id != null) { addQueryParameter("filter_user_id", filter_user_id.toString()) }
            if(filter_username != null && filter_username.isNotEmpty()) { addQueryParameter("filter_username", filter_username) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }

        }.build()
    }
}

@Serializable
data class FollowingGetResponseData (
    val users: Array<User>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
)  {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): FollowingGetResponseData? {
        response.apply {
            if(response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<FollowingGetResponseData>(it) }
        }
    }
}

class FollowingService(val client: AnnictClient) {

    internal fun get(query: FollowingGetRequestQuery): FollowingGetResponseData? {
        this.client.apply {
            return FollowingGetResponseData().parse(
                request(Request.Builder().url(query.url(getUrlBuilder())))
            )
        }
    }
}
