package jp.annict.rest.interfaces

import jp.annict.rest.bases.BaseAnnictClient
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

interface AnnictClient {

    fun getUrlBuilder() : HttpUrl.Builder

    fun request(requestBuilder: Request.Builder) : Response
}