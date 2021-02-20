package com.github.iamtakagi.kannict.services.me

import jp.annict.client.AnnictClient
import jp.annict.enums.Status
import okhttp3.HttpUrl
import okhttp3.Request

data class MeStatuesPostRequestQuery(val work_id: Long? =null,
                                     val kind: Status? =null) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("me/statues")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString())}
            if(kind != null ) { addQueryParameter("kind", kind.toString()) }

        }.build()
    }
}


class MeStatusesService (val client: AnnictClient) {

    internal fun post(query: MeStatuesPostRequestQuery) : Boolean? {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null)).code == 204)
        }
    }
}