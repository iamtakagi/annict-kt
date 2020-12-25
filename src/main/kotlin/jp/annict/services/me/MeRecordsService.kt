package jp.annict.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.client.AnnictClient
import jp.annict.utils.JsonUtil
import jp.annict.enums.RatingState
import jp.annict.models.Record
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeRecordsPostRequestQuery(
    val episode_id: Long? =null,
    val comment: String? =null,
    val rating_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null
) {

    fun url(builder: HttpUrl.Builder): HttpUrl {
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

data class MeRecordsPostResponseData(val record: Record?) {

    constructor() : this(null)

    fun toDataClass(response: Response): MeRecordsPostResponseData {
        return MeRecordsPostResponseData(
            JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
                object : TypeToken<Record>() {}.type
            )
        )
    }
}

data class MeRecordsPatchRequestQuery(
    val episode_id: Long? =null,
    val comment: String? =null,
    val rating_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null
) {

    fun url(builder: HttpUrl.Builder): HttpUrl {
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

data class MeRecordsPatchResponseData(val record: Record?) {

    constructor() : this(null)

    fun toDataClass(response: Response): MeRecordsPatchResponseData {
        return MeRecordsPatchResponseData(
            JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
                object : TypeToken<Record>() {}.type
            )
        )
    }
}

data class MeRecordsDeleteRequestQuery(val id: Long) {

    fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/records/${id}")
        }.build()
    }

}

class MeRecordsService (val client: AnnictClient) {

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
