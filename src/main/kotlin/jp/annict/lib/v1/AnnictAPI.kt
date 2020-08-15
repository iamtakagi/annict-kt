package jp.annict.lib.v1

import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.v1.client.AnnictClientImpl
import okhttp3.OkHttpClient

object AnnictAPI {

    fun createAnnictClient(token: String, httpClient: OkHttpClient) : AnnictClient {
        return AnnictClientImpl(token, httpClient)
    }
}