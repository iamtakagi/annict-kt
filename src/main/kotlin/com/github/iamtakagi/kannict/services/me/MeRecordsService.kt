package com.github.iamtakagi.kannict.services.me

import com.github.iamtakagi.kannict.client.AnnictClient
import com.github.iamtakagi.kannict.enums.RatingState
import com.github.iamtakagi.kannict.exception.AnnictError
import com.github.iamtakagi.kannict.models.Record
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
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

@Serializable
data class MeRecordsPostResponseData(val record: Record? = null) {

    constructor() : this(null)

    internal fun parse(response: Response): MeRecordsPostResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeRecordsPostResponseData>(it) }
        }
    }
}

data class MeRecordsPatchRequestQuery(
    val episode_id: Long? =null,
    val comment: String? =null,
    val rating_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null
) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
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

data class MeRecordsPatchResponseData(val record: Record? = null) {

    constructor() : this(null)

    internal fun parse(response: Response): MeRecordsPatchResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeRecordsPatchResponseData>(it) }
        }
    }
}

data class MeRecordsDeleteRequestQuery(val id: Long) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/records/${id}")
        }.build()
    }

}

class MeRecordsService (val client: AnnictClient) {

    /**
     * エピソードへの記録作成 [write scope]
     */
    internal fun post(query: MeRecordsPostRequestQuery): MeRecordsPostResponseData? {
        this.client.apply {
            return MeRecordsPostResponseData().parse(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("post", null)
                )
            )
        }
    }

    /**
     * 記録編集 [write scope]
     */
    internal fun patch(query: MeRecordsPatchRequestQuery): MeRecordsPatchResponseData? {
        this.client.apply {
            return MeRecordsPatchResponseData().parse(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("patch", null)
                )
            )
        }
    }

    /**
     * 記録削除 [write scope]
     */
    internal fun delete(query: MeRecordsDeleteRequestQuery): Boolean? {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("delete", null)).code == 204)
        }
    }
}
