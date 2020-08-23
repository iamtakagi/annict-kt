package jp.annict.lib.interfaces

import okhttp3.RequestBody

interface RequestBodyData {

    fun toRequestBody() : RequestBody
}