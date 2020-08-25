# annict-kt
Annict API wrapper for Kotlin

[ ![Download](https://api.bintray.com/packages/riptakagi/maven/annict-kt/images/download.svg?version=0.2.2) ](https://bintray.com/riptakagi/maven/annict-kt/0.2.2/link)

https://annict.jp/userland/projects/61

## Document
https://docs.annict.com/docs/

## Installation
```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'jp.annict:annict-kt:${version}'
}
```

## Authorization

```kotlin
// Get Token (アクセストークンを取得する)
val response = AnnictAuth().token("client_id", "client_secret", "authorization_code"(default), "urn:ietf:wg:oauth:2.0:oob"(default), "code")
val access_token = response.access_token
```

```kotlin
// Get Info (認証ユーザの情報を取得する)
val response = AnnictAuth().info("access_token")
```

```kotlin
// Revoke Token (トークンを失効させる)
val result = AnnictAuth().revoke("client_id", "client_secret", "access_token")
```

## Client
```kotlin
// Create Client (クライアント作成)
val client = AnnictClient("access_token")
```

## Service

```kotlin
// 作品情報取得
val response = client.getWorks(filter_title = "氷菓")
val works = response.works
```

```kotlin
// キャスト情報取得
val response = client.getCasts(filter_work_id = 1808, per_page = 5, page = 5)
val casts = response.casts
```
