package jp.annict.services

import com.google.gson.reflect.TypeToken
import jp.annict.client.AnnictClient
import jp.annict.utils.JsonUtil
import jp.annict.enums.Order
import jp.annict.models.Activity
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class ActivitiesGetRequestQuery (
    val fields           : Array<String>? =null,
    val filter_user_id       : Long? =null,
    val filter_username   : String? =null,
    val page             : Long? =null,
    val per_page         : Long? =null,
    val sort_id          : Order? =null
) {

     fun url(builder: HttpUrl.Builder) : HttpUrl {
        return builder.apply {
            addPathSegment("activities")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_user_id != null) { addQueryParameter("filter_user_id", filter_user_id.toString()) }
            if(filter_username != null && filter_username.isNotEmpty()) { addQueryParameter("filter_username", filter_username) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }

        }.build()
    }
}

data class ActivitiesGetResponseData (
    val activities: Array<Activity>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
)  {

    constructor() : this(null, null, null, null)

     fun toDataClass(response: Response): ActivitiesGetResponseData {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return ActivitiesGetResponseData(
            JsonUtil.GSON.fromJson(
                getAsJsonArray("activities"),
                object : TypeToken<Array<Activity>>() {}.type
            ),
            if (get("total_count").isJsonNull) null else get("total_count").asLong,
            if (get("next_page").isJsonNull) null else get("next_page").asLong,
            if (get("prev_page").isJsonNull) null else get("prev_page").asLong
        )
        }
        }
    }
}

class ActivitiesService(val client: AnnictClient) {

     fun get(query: ActivitiesGetRequestQuery) : ActivitiesGetResponseData {
        this.client.apply { return ActivitiesGetResponseData()
            .toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}