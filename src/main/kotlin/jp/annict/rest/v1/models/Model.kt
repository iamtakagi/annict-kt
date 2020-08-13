package jp.annict.rest.v1.models

import jp.annict.rest.v1.enums.Action
import jp.annict.rest.v1.enums.RatingState
import java.util.*

/**
 * 画像関係
 */
data class Facebook(
    val og_image_url: String?
)

data class Twitter(
    val mini_avatar_url    : String?,
    val normal_avatar_url  : String?,
    val bigger_avatar_url  : String?,
    val original_avatar_url: String?,
    val image_url          : String?
)

data class Images(
    val recommended_url: String?,
    val facebook       : Facebook?,
    val twitter        : Twitter?
)

/**
 * ユーザ情報
 */
data class User(
    val id                  : Int?,
    val username            : String?,
    val name                : String?,
    val description         : String?,
    val url                 : String?,
    val avatar_url          : String?,
    val background_image_url: String?,
    val records_count       : Int?,
    val followings_count    : Int?,
    val followers_count     : Int?,
    val wanna_watch_count   : Int?,
    val watching_count      : Int?,
    val watched_count       : Int?,
    val on_hold_count       : Int?,
    val stop_watching_count : Int?,
    val created_at          : Date?
)

data class Status(val kind: Status?)

/**
 * 作品情報
 */
data class Work (
    val id               : Int?,
    val title            : String?,
    val title_kana       : String?,
    val media            : String?,
    val media_text       : String?,
    val season_name      : String?,
    val season_name_text : String?,
    val released_on      : Date?,
    val released_on_about: Date?,
    val official_site_url: String?,
    val wikipedia_url    : String?,
    val twitter_username : String?,
    val twitter_hashtag  : String?,
    val episodes_count   : Int?,
    val watchers_count   : Int?,
    val reviews_count: Int?,
    val syobocal_tid: Int?,
    val mal_anime_id: Int?,
    val images: Images?,
    val no_episodes: Boolean?
)

/**
 * エピソード情報
 */
data class Episode (
    val id            : Int?,
    val number        : Int?,
    val number_text   : String?,
    val sort_number   : Int?,
    val title         : String?,
    val records_count : Int?,
    val record_comments_count : Int?,
    val work          : Work?,
    val prev_episode  : Episode?,
    val next_episode  : Episode?
)

/**
 * エピソードへの記録
 */
data class Record(
    val id            : Int?,
    val comment       : String?,
    val rating        : Int?,
    val is_modified   : Boolean?,
    val rating_state  : RatingState?,
    val likes_count   : Int?,
    val comments_count: Int?,
    val created_at    : Date?,
    val user          : User?,
    val work          : Work?,
    val episode       : Episode?
)

/**
 * アクティビティ情報
 */
data class Activity(
    val id        : Int?,
    val user      : User?,
    val action    : Action,
    val created_at: Date?,
    val episode   : Episode?,
    val record    : Record?
)

data class Series (
    val id: Int?,
    val name: String?,
    val name_ro: String?,
    val name_en: String?
)

/**
 * キャラクター情報
 */
data class Character(
    val id: Int?,
    val name_kana: String?,
    val name_en: String?,
    val nickname: String?,
    val birthday: String?,
    val birthday_en: String?,
    val age: String?,
    val age_en: String?,
    val blood_type: String?,
    val blood_type_en: String?,
    val height: String?,
    val height_en: String?,
    val weight: String?,
    val weight_en: String?,
    val nationality: String?,
    val nationality_en: String?,
    val occupation: String?,
    val occupation_en: String?,
    val description_source: String?,
    val description_source_en: String?,
    val favorite_characters_count: Int?,
    val series: Series?
)

/**
 * 都道府県情報
 */
data class Prefecture(
    val id: Int?,
    val name: String?
)

/**
 * 人物(声優)情報
 */
data class Person(
    val id: Int?,
    val name: String?,
    val name_kana: String?,
    val name_en: String?,
    val nickname: String?,
    val nickname_en: String?,
    val gender_text: String?,
    val url: String?,
    val url_en: String?,
    val wikipedia_url: String?,
    val wikipedia_url_en: String?,
    val twitter_username: String?,
    val twitter_username_en: String?,
    val birthday: String?,
    val blood_type: String?,
    val height: Int?,
    val favorite_people_count: Int?,
    val casts_count: Int?,
    val staffs_count: Int?,
    val prefecture: Prefecture?
)

/**
 * キャスト情報
 */
data class Cast(
    val id: Int?,
    val name: String?,
    val name_en: String?,
    val sort_number: Int?,
    val work: Work?,
    val character: Character?,
    val person: Person?
)

