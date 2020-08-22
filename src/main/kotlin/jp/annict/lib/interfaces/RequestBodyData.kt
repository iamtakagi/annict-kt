package jp.annict.lib.interfaces

import com.google.gson.JsonObject
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody

interface RequestBodyData {

    fun toRequestBody() : RequestBody
}