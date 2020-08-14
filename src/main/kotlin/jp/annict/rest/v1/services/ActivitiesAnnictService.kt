package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.bases.BaseAnnictService
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.enums.Order
import jp.annict.rest.v1.models.Activity
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class ActivitiesRequestQuery (
    val fields           : Array<String>?,
    val filter_user_id       : Int?,
    val filter_username   : String?,
    val page             : Int?,
    val per_page         : Int?,
    val sort_id          : Order?
) : RequestQuery {

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

data class ActivitiesResponseData(
    val activities: Array<Activity>?
) : ResponseData<ActivitiesResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): ActivitiesResponseData {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return ActivitiesResponseData (
            JsonUtil.GSON.fromJson(getAsJsonArray("activities"), object : TypeToken<Array<Activity>>() {}.type)) } }
    }
}

class ActivitiesAnnictService(client: AnnictClient) : BaseAnnictService(client){

    fun get(query: ActivitiesRequestQuery) : ActivitiesResponseData {
        this.client.apply { return ActivitiesResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}