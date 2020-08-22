package jp.annict.lib

import jp.annict.lib.enums.AnnictVersion
import jp.annict.lib.interfaces.AnnictClient
import okhttp3.OkHttpClient

object AnnictLib {

    fun newClient(token: String, version: AnnictVersion) : AnnictClient {
        when(version) {
            AnnictVersion.V_1 -> {
                return jp.annict.lib.v1.client.AnnictClientImpl(token, OkHttpClient())
            }
        }
    }
}