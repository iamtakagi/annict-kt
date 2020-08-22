package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.enums.Order
import jp.annict.lib.v1.enums.Status
import jp.annict.lib.v1.models.MeWork
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
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/works")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_season != null && filter_season.isNotEmpty()) { addQueryParameter("filter_season", filter_season.toString()) }
            if(filter_title != null && filter_title.isNotEmpty()) { addQueryParameter("filter_title", filter_title.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_season != null) { addQueryParameter("sort_season", sort_season.name) }
            if(sort_watchers_count != null) { addQueryParameter("sort_watchers_count", sort_watchers_count.name) }
            if(filter_status != null) { addQueryParameter("filter_status", filter_status.name) }

        }.build()
    }
}

data class MeWorksGetResponseData (
    val works: Array<MeWork>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) : ResponseData<MeWorksGetResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): MeWorksGetResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return MeWorksGetResponseData (
                    JsonUtil.GSON.fromJson(getAsJsonArray("works"), object : TypeToken<Array<MeWork>>() {}.type),
                    if (get("total_count").isJsonNull) null else get("total_count").asLong,
                    if (get("next_page").isJsonNull) null else get("next_page").asLong,
                    if (get("prev_page").isJsonNull) null else get("prev_page").asLong
                )
            }
        }
    }

}

class MeWorksService(client: IAnnictClient) : BaseService(client) {

    fun get(query: MeWorksGetRequestQuery) : MeWorksGetResponseData {
        this.client.apply {
            return MeWorksGetResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}