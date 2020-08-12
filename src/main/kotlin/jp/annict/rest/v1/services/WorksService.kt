package jp.annict.rest.v1.services

import java.util.*

data class Work (
    val id                : Int,
    val title             : String,
    val title_kana        : String,
    val media             : String,
    val media_text        : String,
    val season_name       : String,
    val season_name_text  : String,
    val released_on       : Date,
    val released_on_about : Date,
    val official_site_url : String,
    val wikipedia_url     : String,
    val twitter_username  : String,
    val twitter_hashtag   : String,
    val episodes_count    : Int,
    val watchers_count    : Int
)