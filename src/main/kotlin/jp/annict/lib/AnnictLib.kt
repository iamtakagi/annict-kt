package jp.annict.lib

import okhttp3.OkHttpClient

object AnnictLib {

    fun newClient(token: String) : jp.annict.lib.v1.client.AnnictClient {
        return jp.annict.lib.v1.client.AnnictClient(token, OkHttpClient())
    }
}