package jp.annict.lib

import jp.annict.lib.interfaces.AnnictClient
import okhttp3.OkHttpClient

object AnnictLib {

    fun newClient(token: String, httpClient: OkHttpClient) : AnnictClient {
        return jp.annict.lib.v1.client.AnnictClientImpl(token, httpClient)
    }
}