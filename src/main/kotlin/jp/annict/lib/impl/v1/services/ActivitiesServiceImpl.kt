package jp.annict.lib.impl.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.services.ActivitiesGetRequestQuery
import jp.annict.lib.interfaces.services.ActivitiesGetResponseData
import jp.annict.lib.interfaces.services.ActivitiesService
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.impl.v1.enums.Order
import jp.annict.lib.impl.v1.models.Activity
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class ActivitiesGetRequestQueryImpl (
    val fields           : Array<String>? =null,
    val filter_user_id       : Long? =null,
    val filter_username   : String? =null,
    val page             : Long? =null,
    val per_page         : Long? =null,
    val sort_id          : Order? =null
) : ActivitiesGetRequestQuery {

    override fun url(builder: HttpUrl.Builder) : HttpUrl {
        return builder.apply {
            addPathSegment("activities")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_user_id != null) { addQueryParameter("filter_user_id", filter_user_id.toString()) }
            if(filter_username != null && filter_username.isNotEmpty()) { addQueryParameter("filter_username", filter_username) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }

        }.build()
    }
}

data class ActivitiesGetResponseDataImpl(
    val activities: Array<Activity>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) : ActivitiesGetResponseData {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): ActivitiesGetResponseData {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return ActivitiesGetResponseDataImpl (
            JsonUtil.GSON.fromJson(getAsJsonArray("activities"), object : TypeToken<Array<Activity>>() {}.type),
            if (get("total_count").isJsonNull) null else get("total_count").asLong,
            if (get("next_page").isJsonNull) null else get("next_page").asLong,
            if (get("prev_page").isJsonNull) null else get("prev_page").asLong
        )
        }
        }
    }
}

class ActivitiesServiceImpl(val client: IAnnictClient) : ActivitiesService {

    override fun get(query: ActivitiesGetRequestQuery) : ActivitiesGetResponseData {
        this.client.apply { return ActivitiesGetResponseDataImpl()
            .toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}