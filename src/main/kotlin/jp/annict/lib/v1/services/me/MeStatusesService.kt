package jp.annict.lib.v1.services.me

import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.v1.enums.Status
import okhttp3.HttpUrl
import okhttp3.Request

data class MeStatuesPostRequestQuery(val work_id: Long?, val kind: Status?) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("me/statues")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString())}
            if(kind != null ) { addQueryParameter("kind", kind.name) }

        }.build()
    }
}

class MeStatusesService(client: AnnictClient) : BaseService(client) {

    fun post(query: MeStatuesPostRequestQuery) : Boolean {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null)).code == 204)
        }
    }
}