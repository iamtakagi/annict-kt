package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.enums.Order
import jp.annict.lib.v1.models.Program
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

class MeProgramsGetRequestQuery(
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
    ) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
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
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_started_at != null) { addQueryParameter("sort_started_at", sort_started_at.name) }

        }.build()
    }
}

data class MeProgramsGetResponseData(
    val programs: Array<Program>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) : ResponseData<MeProgramsGetResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): MeProgramsGetResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return MeProgramsGetResponseData (
                JsonUtil.GSON.fromJson(getAsJsonArray("activities"), object : TypeToken<Array<Program>>() {}.type),
                if (get("total_count").isJsonNull) null else get("total_count").asLong,
                if (get("next_page").isJsonNull) null else get("next_page").asLong,
                if (get("prev_page").isJsonNull) null else get("prev_page").asLong
            ) }
        }
    }
}

class MeProgramsService(client: IAnnictClient) : BaseService(client) {

    fun get(query: MeProgramsGetRequestQuery) : MeProgramsGetResponseData {
        this.client.apply { return MeProgramsGetResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}