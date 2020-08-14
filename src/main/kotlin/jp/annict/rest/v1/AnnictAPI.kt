package jp.annict.rest.v1

import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.v1.client.AnnictClientImpl
import okhttp3.OkHttpClient

object AnnictAPI {

    fun createAnnictClient(token: String, httpClient: OkHttpClient) : AnnictClient {
        return AnnictClientImpl(token, httpClient)
    }
}