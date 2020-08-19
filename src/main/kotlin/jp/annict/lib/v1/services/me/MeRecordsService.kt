package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.enums.RatingState
import jp.annict.lib.v1.models.Record
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeRecordsPostRequestQuery(
    val episode_id: Long?,
    val comment: String?,
    val rating_state: RatingState?,
    val share_twitter: Boolean?,
    val share_facebook: Boolean?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/records")

            if(episode_id != null) { addQueryParameter("episode_id", episode_id.toString()) }
            if(comment != null && comment.isNotEmpty()) { addQueryParameter("comment", comment) }
            if(rating_state != null) { addQueryParameter("rating_state", rating_state.name) }
            if(share_twitter != null ) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null ) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeRecordsPostResponseData(val record: Record?) : ResponseData<MeRecordsPostResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeRecordsPostResponseData {
        return MeRecordsPostResponseData (
            JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject, object : TypeToken<Record>() {}.type)
        )
    }
}

data class MeRecordsPatchRequestQuery(
    val episode_id: Long?,
    val comment: String?,
    val rating_state: RatingState?,
    val share_twitter: Boolean?,
    val share_facebook: Boolean?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/records")

            if(episode_id != null) { addQueryParameter("episode_id", episode_id.toString()) }
            if(comment != null && comment.isNotEmpty()) { addQueryParameter("comment", comment) }
            if(rating_state != null) { addQueryParameter("rating_state", rating_state.name) }
            if(share_twitter != null ) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null ) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeRecordsPatchResponseData(val record: Record?) : ResponseData<MeRecordsPatchResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeRecordsPatchResponseData {
        return MeRecordsPatchResponseData (
            JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject, object : TypeToken<Record>() {}.type)
        )
    }
}

data class MeRecordsDeleteRequestQuery(val id: Long) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/records/${id}")
        }.build()
    }

}

class MeRecordsService(client: AnnictClient) : BaseService(client) {

    /**
     * エピソードへの記録作成 [write scope]
     */
    fun post(query: MeRecordsPostRequestQuery): MeRecordsPostResponseData {
        this.client.apply {
            return MeRecordsPostResponseData().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("post", null)
                )
            )
        }
    }

    /**
     * 記録編集 [write scope]
     */
    fun patch(query: MeRecordsPatchRequestQuery): MeRecordsPatchResponseData {
        this.client.apply {
            return MeRecordsPatchResponseData().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("patch", null)
                )
            )
        }
    }

    /**
     * 記録削除 [write scope]
     */
    fun delete(query: MeRecordsDeleteRequestQuery): Boolean {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("delete", null)).code == 204)
        }
    }
}
