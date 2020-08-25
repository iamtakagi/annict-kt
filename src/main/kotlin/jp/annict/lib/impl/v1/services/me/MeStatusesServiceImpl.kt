package jp.annict.lib.impl.v1.services.me

import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.services.me.MeStatuesPostRequestQuery
import jp.annict.lib.interfaces.services.me.MeStatusesService
import jp.annict.lib.impl.v1.enums.Status
import okhttp3.HttpUrl
import okhttp3.Request

data class MeStatuesPostRequestQueryImpl(val work_id: Long? =null,
                                     val kind: Status? =null) : MeStatuesPostRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegments("me/statues")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString())}
            if(kind != null ) { addQueryParameter("kind", kind.toString()) }

        }.build()
    }
}

class MeStatusesServiceImpl (val client: IAnnictClient) : MeStatusesService {

   override fun post(query: MeStatuesPostRequestQuery) : Boolean {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null)).code == 204)
        }
    }
}