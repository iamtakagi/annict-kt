package jp.annict.rest.interfaces

import okhttp3.HttpUrl

interface RequestQuery {

    fun url(builder: HttpUrl.Builder): HttpUrl
}
