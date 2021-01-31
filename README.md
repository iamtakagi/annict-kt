<p align="center"><a href="https://annict.com" target="_blank" rel="noopener"><img src="https://user-images.githubusercontent.com/56767/56467671-fdd6ea80-645c-11e9-9056-a5d3fd5739e6.png" width="130" /></a></p>

# annict-kt
[Annict API](https://github.com/annict/annict) wrapper for Kotlin Library.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.21-blue?style=flat-square)](https://kotlinlang.org)
[![annict-kt](https://img.shields.io/github/v/release/iam-takagi/annict-kt?style=flat-square)](https://github.com/iam-takagi/annict-kt/releases/latest)
[![license](https://img.shields.io/github/license/iam-takagi/annict-kt?style=flat-square)](./LICENSE)
[![issues](https://img.shields.io/github/issues/iam-takagi/annict-kt?style=flat-square)](https://github.com/iam-takagi/annict-kt/issues)
[![pull requests](https://img.shields.io/github/issues-pr/iam-takagi/annict-kt?style=flat-square)](https://github.com/iam-takagi/annict-kt/pulls)

https://annict.jp/userland/projects/61

## Document
https://iam-takagi.github.io/annict-kt/kdoc/pages/index.html

https://docs.annict.com/docs/

## Installation
[ ![Download](https://api.bintray.com/packages/riptakagi/maven/annict-kt/images/download.svg?version=2.4.2) ](https://bintray.com/riptakagi/maven/annict-kt/2.4.2/link)


```gradle
repositories {
    maven ( url = "https://dl.bintray.com/riptakagi/maven/")
}

dependencies {
    implementation 'jp.annict:annict-kt:2.4.2'
}
```

or

```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'jp.annict:annict-kt:2.4.2'
}
```

## Example

### Authorization

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

### Client
```kotlin
// Create Client (クライアント作成)
val client = AnnictClient("access_token")
```

### Service

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
