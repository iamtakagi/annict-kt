package jp.annict.rest.v1.models

import jp.annict.rest.v1.enums.Action
import jp.annict.rest.v1.enums.RatingState
import java.util.*

data class User(
    val id                   : Int?,
    val username             : String?,
    val name                 : String?,
    val description          : String?,
    val avatar_url           : String?,
    val background_image_url : String?,
    val url                  : String?,
    val records_count        : Int?,
    val created_at           : Date?
)

data class Facebook(
    val og_image_url: String?
)

data class Twitter(
    val mini_avatar_url: String?,
    val normal_avatar_url: String?,
    val bigger_avatar_url: String?,
    val original_avatar_url: String?,
    val image_url: String?
)

data class Images(
    val recommended_url: String?,
    val facebook: Facebook?,
    val twitter: Twitter?
)

data class Work (
    val id                : Int?,
    val title             : String?,
    val title_kana        : String?,
    val media             : String?,
    val media_text        : String?,
    val season_name       : String?,
    val season_name_text  : String?,
    val released_on       : Date?,
    val released_on_about : Date?,
    val official_site_url : String?,
    val wikipedia_url     : String?,
    val twitter_username  : String?,
    val twitter_hashtag   : String?,
    val episodes_count    : Int?,
    val watchers_count    : Int?,
    val images: Images?
)

data class Episode (
    val id            : Int?,
    val number        : Int?,
    val number_text   : String?,
    val sort_number   : Int?,
    val title         : String?,
    val records_count : Int?,
    val work          : Work?,
    val prev_episode  : Episode?,
    val next_episode  : Episode?
)

data class Record(
    val id             : Int?,
    val comment        : String?,
    val rating         : Int?,
    val rating_state: RatingState?,
    val is_modified    : Boolean?,
    val likes_count    : Int?,
    val comments_count : Int?,
    val created_at     : Date?,
    val user           : User?,
    val work           : Work?,
    val episode        : Episode?
)

class Activity(
    val id         : Int?,
    val user       : User?,
    val action     : Action,
    val created_at : Date?,
    val episode    : Episode?,
    val record     : Record?
)