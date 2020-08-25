package jp.annict.lib.impl.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.services.me.*
import jp.annict.lib.interfaces.services.me.MeRecordsService
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.impl.v1.enums.RatingState
import jp.annict.lib.impl.v1.models.Record
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeRecordsPostRequestQueryImpl(
    val episode_id: Long? =null,
    val comment: String? =null,
    val rating_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null
) : MeRecordsPostRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/records")

            if(episode_id != null) { addQueryParameter("episode_id", episode_id.toString()) }
            if(comment != null && comment.isNotEmpty()) { addQueryParameter("comment", comment) }
            if(rating_state != null) { addQueryParameter("rating_state", rating_state.toString()) }
            if(share_twitter != null ) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null ) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeRecordsPostResponseDataImpl(val record: Record?) : MeRecordsPostResponseData {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeRecordsPostResponseData {
        return MeRecordsPostResponseDataImpl (
            JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject, object : TypeToken<Record>() {}.type)
        )
    }
}

data class MeRecordsPatchRequestQueryImpl(
    val episode_id: Long? =null,
    val comment: String? =null,
    val rating_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null
) : MeRecordsPatchRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("/me/records")

            if(episode_id != null) { addQueryParameter("episode_id", episode_id.toString()) }
            if(comment != null && comment.isNotEmpty()) { addQueryParameter("comment", comment) }
            if(rating_state != null) { addQueryParameter("rating_state", rating_state.toString()) }
            if(share_twitter != null ) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null ) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeRecordsPatchResponseDataImpl(val record: Record?) : MeRecordsPatchResponseData {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeRecordsPatchResponseData {
        return MeRecordsPatchResponseDataImpl (
            JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject, object : TypeToken<Record>() {}.type)
        )
    }
}

data class MeRecordsDeleteRequestQueryImpl(val id: Long) : MeRecordsDeleteRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/records/${id}")
        }.build()
    }

}

class MeRecordsServiceImpl (val client: IAnnictClient) : MeRecordsService {

    /**
     * エピソードへの記録作成 [write scope]
     */
    override fun post(query: MeRecordsPostRequestQuery): MeRecordsPostResponseData {
        this.client.apply {
            return MeRecordsPostResponseDataImpl().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("post", null)
                )
            )
        }
    }

    /**
     * 記録編集 [write scope]
     */
    override fun patch(query: MeRecordsPatchRequestQuery): MeRecordsPatchResponseData {
        this.client.apply {
            return MeRecordsPatchResponseDataImpl().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("patch", null)
                )
            )
        }
    }

    /**
     * 記録削除 [write scope]
     */
    override fun delete(query: MeRecordsDeleteRequestQuery): Boolean {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("delete", null)).code == 204)
        }
    }
}
