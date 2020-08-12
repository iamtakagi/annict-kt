package jp.annict.rest.v1.services

import java.util.*

data class User(
    val id            : Int,
    val username      : String,
    val name          : String,
    val description   : String,
    val url           : String,
    val records_count : Int,
    val created_at    : Date
)

data class Record(
    val id             : Int,
    val comment        : String,
    val rating         : Int,
    val is_modified    : Boolean,
    val likes_count    : Int,
    val comments_count : Int,
    val created_at     : Date,
    val user           : User,
    val work           : Work,
    val episode        : Episode
)
