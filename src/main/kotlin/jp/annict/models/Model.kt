package jp.annict.models

import jp.annict.enums.Action
import jp.annict.enums.RatingState
import java.util.*

/**
 * 画像関係
 */
data class Facebook(
    val og_image_url: String?
)

data class Twitter(
    val mini_avatar_url     : String?,
    val normal_avatar_url   : String?,
    val bigger_avatar_url   : String?,
    val original_avatar_url : String?,
    val image_url           : String?
)

data class Images(
    val recommended_url : String?,
    val facebook        : Facebook?,
    val twitter         : Twitter?
)

/**
 * ユーザ情報
 */
data class User(
    val id                   : Long?,
    val username             : String?,
    val name                 : String?,
    val description          : String?,
    val url                  : String?,
    val avatar_url           : String?,
    val background_image_url : String?,
    val records_count        : Long?,
    val followings_count     : Long?,
    val followers_count      : Long?,
    val wanna_watch_count    : Long?,
    val watching_count       : Long?,
    val watched_count        : Long?,
    val on_hold_count        : Long?,
    val stop_watching_count  : Long?,
    val created_at           : Date?
)

data class Status(val kind: Status?)

/**
 * 作品情報
 */
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
data class MeWork(val kind: Status) : Work()

/**
 * エピソード情報
 */
data class Episode (
    val id                    : Long?,
    val number                : Long?,
    val number_text           : String?,
    val sort_number           : Long?,
    val title                 : String?,
    val records_count         : Long?,
    val record_comments_count : Long?,
    val work                  : Work?,
    val prev_episode          : Episode?,
    val next_episode          : Episode?
)

/**
 * エピソードへの記録
 */
data class Record(
    val id             : Long?,
    val comment        : String?,
    val rating         : Long?,
    val is_modified    : Boolean?,
    val rating_state   : RatingState?,
    val likes_count    : Long?,
    val comments_count : Long?,
    val created_at     : Date?,
    val user           : User?,
    val work           : Work?,
    val episode        : Episode?
)

/**
 * アクティビティ情報
 */
data class Activity (
    val id         : Long?,
    val user       : User?,
    val action     : Action,
    val created_at : Date?,
    val episode    : Episode?,
    val record     : Record?
)

/**
 * シリーズ情報
 */
data class Series (
    val id      : Long?= null,
    val name    : String?= null,
    val name_ro : String?= null,
    val name_en : String?= null
)

/**
 * キャラクター情報
 */
data class Character (
    val id                        : Long?= null,
    val name                      : String?= null,
    val name_kana                 : String?= null,
    val name_en                   : String?= null,
    val nickname                  : String?= null,
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
    val description_source        : String?= null,
    val description_source_en     : String?= null,
    val favorite_characters_count : Long?= null,
    val series                    : Series?=null
)

/**
 * 都道府県情報
 */
data class Prefecture (
    val id   : Long?= null,
    val name : String?= null
)

/**
 * 人物(声優)情報
 */
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
data class Me (
    val id                   : Long?,
    val username             : String?,
    val name                 : String?,
    val description          : String?,
    val url                  : String?,
    val avatar_url           : String?,
    val background_image_url : String?,
    val records_count        : Long?,
    val followings_count     : Long?,
    val followers_count      : Long?,
    val wanna_watch_count    : Long?,
    val watching_count       : Long?,
    val watched_count        : Long?,
    val on_hold_count        : Long?,
    val stop_watching_count  : Long?,
    val created_at           : Date?,
    val email                : String?,
    val notifications_count  : Long?
)

/**
 *　チャンネル情報
 */
data class Channel (
    val id   : Long?,
    val name : String?
)

/**
 * 放送予定情報
 */
data class Program (
    val id             : Long?,
    val started_at     : Date?,
    val is_rebroadcast : Boolean?,
    val channel        : Channel?,
    val work           : Work?,
    val episode        : Episode?
)

/**
 * 団体(会社)情報
 */
data class Organization (
    val id                           : Long?,
    val name                         : String?,
    val name_kana                    : String?,
    val name_en                      : String?,
    val url                          : String?,
    val url_en                       : String?,
    val wikipedia_url                : String?,
    val wikipedia_url_en             : String?,
    val twitter_username             : String?,
    val twitter_username_en          : String?,
    val favorite_organizations_count : Long?,
    val staffs_count                 : Long?
)

/**
 * スタッフ情報
 */
data class Staff (
    val id            : Long?,
    val name          : String?,
    val name_en       : String?,
    val role_text     : String?,
    val role_other    : String?,
    val role_other_en : String?,
    val sort_number   : String?,
    val work          : String?,
    val organization  : Organization?
)

/**
 * 作品へのレビュー情報
 */
data class Review (
    val id                     : Long?,
    val title                  : String?,
    val body                   : String?,
    val rating_animation_state : RatingState?,
    val rating_music_state     : RatingState?,
    val rating_story_state     : RatingState?,
    val rating_character_state : RatingState?,
    val rating_overall_state   : RatingState?,
    val likes_count            : Long?,
    val impressions_count      : Long?,
    val modified_at            : Date?,
    val created_at             : Date?,
    val user                   : User?,
    val work                   : Work?,
    val episodes_count: Long?,
    val watchers_count: Long?,
    val season_name: String?,
    val season_name_text: String?
)

data class Application(val uid: String?)