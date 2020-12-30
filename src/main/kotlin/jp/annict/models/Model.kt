package jp.annict.models

import jp.annict.enums.Action
import jp.annict.enums.RatingState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.internal.http.StatusLine
import java.util.*

/**
 * 画像関係
 */
@Serializable
data class Facebook(
    val og_image_url: String? = null
)

@Serializable
data class Twitter(
    val mini_avatar_url     : String? = null,
    val normal_avatar_url   : String? = null,
    val bigger_avatar_url   : String? = null,
    val original_avatar_url : String? = null,
    val image_url           : String? = null
)

@Serializable
data class Images(
    val recommended_url : String? = null,
    val facebook        : Facebook? = null,
    val twitter         : Twitter? = null
)

/**
 * ユーザ情報
 */
@Serializable
data class User(
    val id                   : Long? = null,
    val username             : String? = null,
    val name                 : String? = null,
    val description          : String? = null,
    val url                  : String? = null,
    val avatar_url           : String? = null,
    val background_image_url : String? = null,
    val records_count        : Long? = null,
    val followings_count     : Long? = null,
    val followers_count      : Long? = null,
    val wanna_watch_count    : Long? = null,
    val watching_count       : Long? = null,
    val watched_count        : Long? = null,
    val on_hold_count        : Long? = null,
    val stop_watching_count  : Long? = null,
    val created_at           : String? = null
)

/**
 * 作品情報
 */
@Serializable
open class Work (
    val id                : Long? = null,
    val title             : String? = null,
    val title_kana        : String? = null,
    val media             : String? = null,
    val media_text        : String? = null,
    val season_name       : String? = null,
    val season_name_text  : String? = null,
    val released_on       : String? = null,
    val released_on_about : String? = null,
    val official_site_url : String? = null,
    val wikipedia_url     : String? = null,
    val twitter_username  : String? = null,
    val twitter_hashtag   : String? = null,
    val episodes_count    : Long? = null,
    val watchers_count    : Long? = null,
    val reviews_count     : Long? = null,
    val syobocal_tid      : String? = null,
    val mal_anime_id      : String? = null,
    val images            : Images? = null,
    val no_episodes       : Boolean? = null
)

/***
 * 自分の見た作品情報
 */
@Serializable
data class MeWork(val status: Status? = null) : Work()

/**
 * エピソード情報
 */

@Serializable
data class Episode (
    val id                    : Long? = null,
    val number                : Double? = null,
    val number_text           : String? = null,
    val sort_number           : Long? = null,
    val title                 : String? = null,
    val records_count         : Long? = null,
    val record_comments_count : Long? = null,
    val work                  : Work? = null,
    val prev_episode          : Episode?  = null,
    val next_episode          : Episode?  = null
)

/**
 * エピソードへの記録
 */
@Serializable
data class Record(
    val id             : Long? = null,
    val comment        : String? = null,
    val rating         : Long? = null,
    val is_modified    : Boolean? = null,
    val rating_state   : RatingState? = null,
    val likes_count    : Long? = null,
    val comments_count : Long? = null,
    val created_at     : String? = null,
    val user           : User? = null,
    val work           : Work? = null,
    val episode        : Episode? = null
)

/**
 * アクティビティ情報
 */
@Serializable
data class Activity (
    val id         : Long?  = null,
    val user       : User?  = null,
    val action     : Action? = null,
    val created_at : String?  = null,
    val work: Work?  = null,
    val episode: Episode? = null,
    val record: Record?  = null,
    val status: Status?  = null
)

@Serializable
data class Status(
        val kind: jp.annict.enums.Status? = null
)

/**
 * シリーズ情報
 */
@Serializable
data class Series (
    val id      : Long?= null,
    val name    : String?= null,
    val name_ro : String?= null,
    val name_en : String?= null
)

/**
 * キャラクター情報
 */
@Serializable
data class Character (
    val id                        : Long?= null,
    val name                      : String?= null,
    val name_kana                 : String?= null,
    val name_en                   : String?= null,
    val nickname                  : String?= null,
    val nickname_en               : String?= null,
    val birthday                  : String?= null,
    val birthday_en               : String?= null,
    val age                       : String?= null,
    val age_en                    : String?= null,
    val blood_type                : String?= null,
    val blood_type_en             : String?= null,
    val height                    : String?= null,
    val height_en                 : String?= null,
    val weight                    : String?= null,
    val weight_en                 : String?= null,
    val nationality               : String?= null,
    val nationality_en            : String?= null,
    val occupation                : String?= null,
    val occupation_en             : String?= null,
    val description               : String?= null,
    val description_en            : String?= null,
    val description_source        : String?= null,
    val description_source_en     : String?= null,
    val favorite_characters_count : Long?= null,
    val series                    : Series?=null
)

/**
 * 都道府県情報
 */
@Serializable
data class Prefecture (
    val id   : Long?= null,
    val name : String?= null
)

/**
 * 人物(声優)情報
 */
@Serializable
data class Person (
    val id                    : Long?= null,
    val name                  : String?= null,
    val name_kana             : String?= null,
    val name_en               : String?= null,
    val nickname              : String?= null,
    val nickname_en           : String?= null,
    val gender_text           : String?= null,
    val url                   : String?= null,
    val url_en                : String?= null,
    val wikipedia_url         : String?= null,
    val wikipedia_url_en      : String?= null,
    val twitter_username      : String?= null,
    val twitter_username_en   : String?= null,
    val birthday              : String?= null,
    val blood_type            : String?= null,
    val height                : String?= null,
    val favorite_people_count : Long?= null,
    val casts_count           : Long?= null,
    val staffs_count          : Long?= null,
    val prefecture            : Prefecture?= null
)

/**
 * キャスト情報
 */
@Serializable
data class Cast (
    val id          : Long?= null,
    val name        : String?= null,
    val name_en     : String?= null,
    val sort_number : Long?= null,
    val work        : Work?= null,
    val character   : Character?= null,
    val person      : Person?= null
)

/**
 * プロフィール情報
 */
@Serializable
open class Me (
    val id: Long? = null,
    val username             : String? = null,
    val name                 : String? = null,
    val description          : String? = null,
    val url                  : String? = null,
    val avatar_url           : String? = null,
    val background_image_url : String? = null,
    val records_count        : Long? = null,
    val followings_count     : Long? = null,
    val followers_count      : Long? = null,
    val wanna_watch_count    : Long? = null,
    val watching_count       : Long? = null,
    val watched_count        : Long? = null,
    val on_hold_count        : Long? = null,
    val stop_watching_count  : Long? = null,
    val created_at           : String? = null,
    val email                : String? = null,
    val notifications_count  : Long? = null
)

/**
 *　チャンネル情報
 */
@Serializable
data class Channel (
    val id   : Long? = null,
    val name : String? = null
)

/**
 * 放送予定情報
 */
@Serializable
data class Program (
    val id             : Long? = null,
    val started_at     : String? = null,
    val is_rebroadcast : Boolean? = null,
    val channel        : Channel? = null,
    val work           : Work? = null,
    val episode        : Episode? = null
)

/**
 * 団体(会社)情報
 */
@Serializable
data class Organization (
    val id                           : Long? = null,
    val name                         : String? = null,
    val name_kana                    : String? = null,
    val name_en                      : String? = null,
    val url                          : String? = null,
    val url_en                       : String? = null,
    val wikipedia_url                : String? = null,
    val wikipedia_url_en             : String? = null,
    val twitter_username             : String? = null,
    val twitter_username_en          : String? = null,
    val favorite_organizations_count : Long? = null,
    val staffs_count                 : Long? = null
)

/**
 * スタッフ情報
 */
@Serializable
data class Staff (
    val id            : Long? = null,
    val name          : String? = null,
    val name_en       : String? = null,
    val role_text     : String? = null,
    val role_other    : String? = null,
    val role_other_en : String? = null,
    val sort_number   : Long? = null,
    val work          : Work? = null,
    val organization  : Organization? = null,
    val person  : Person? = null
)

/**
 * 作品へのレビュー情報
 */
@Serializable
data class Review (
    val id                     : Long? = null,
    val title                  : String? = null,
    val body                   : String? = null,
    val rating_animation_state : RatingState? = null,
    val rating_music_state     : RatingState? = null,
    val rating_story_state     : RatingState? = null,
    val rating_character_state : RatingState? = null,
    val rating_overall_state   : RatingState? = null,
    val likes_count            : Long? = null,
    val impressions_count      : Long? = null,
    val modified_at            : String? = null,
    val created_at             : String? = null,
    val user                   : User? = null,
    val work                   : Work? = null,
    val episodes_count: Long? = null,
    val watchers_count: Long? = null,
    val season_name: String? = null,
    val season_name_text: String? = null
)

@Serializable
data class Application(val uid: String? = null)