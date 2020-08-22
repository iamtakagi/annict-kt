package jp.annict.lib.interfaces

import okhttp3.RequestBody

interface FormBodyData {

    fun toFormBody() : RequestBody
}