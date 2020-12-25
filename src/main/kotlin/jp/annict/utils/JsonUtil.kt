package jp.annict.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.util.*


object JsonUtil {

    val GSON = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    val JSON_PARSER = JsonParser()
}