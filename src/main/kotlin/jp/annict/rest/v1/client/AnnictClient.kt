package jp.annict.rest.v1.client

import jp.annict.rest.bases.BaseAnnictClient
import okhttp3.*

class AnnictClient(val token: String, httpClient: OkHttpClient) : BaseAnnictClient(httpClient) {

    override fun getUrlBuilder() : HttpUrl.Builder {
        return HttpUrl.Builder()
            .scheme("https")
            .host("api.annict.com")
            .addPathSegment("v1")
    }

    override fun request(requestBuilder: Request.Builder): Response {
        return this.client.newCall(requestBuilder.header("Authorization", "Bearer $token").build()).execute()
    }


}