package jp.annict.services.me

import jp.annict.client.AnnictClient
import jp.annict.enums.Action
import jp.annict.enums.Order
import jp.annict.exception.AnnictError
import jp.annict.models.Activity
import jp.annict.services.SeriesGetResponseData
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeFollowingActivitiesGetRequestQuery (
    val fields: Array<String>? =null,
    val filter_actions: Array<Action>? =null,
    val filter_muted: Boolean? =null,
    val page: Long? =null,
    val per_page: Long? =null,
    val sort_id: Order?=null
) {

     internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/following_activities")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_actions != null && filter_actions.isNotEmpty()) { addQueryParameter("filter_actions", filter_actions.joinToString(separator = ",")) }
            if(filter_muted != null) { addQueryParameter("filter_muted", filter_muted.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }

        }.build()
    }
}

@Serializable
data class MeFollowingActivitiesGetResponseData (
    val activities: Array<Activity>? = null,
    val total_count: Long? = null,
    val next_page: Long? = null,
    val prev_page: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): MeFollowingActivitiesGetResponseData? {
        response.apply {
            if(response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeFollowingActivitiesGetResponseData>(it) }
        }
    }
}

class MeFollowingActivitiesService (val client: AnnictClient) {

    internal fun get(query: MeFollowingActivitiesGetRequestQuery) : MeFollowingActivitiesGetResponseData? {
        this.client.apply { return MeFollowingActivitiesGetResponseData()
            .parse(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }

}