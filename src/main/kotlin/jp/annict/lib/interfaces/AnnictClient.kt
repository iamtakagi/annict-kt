package jp.annict.lib.interfaces

import jp.annict.lib.enums.AnnictVersion
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

interface AnnictClient {

    fun getVersion() : AnnictVersion

    fun getUrlBuilder() : HttpUrl.Builder

    fun request(requestBuilder: Request.Builder) : Response
}