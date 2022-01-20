<p align="center"><a href="https://annict.com" target="_blank" rel="noopener"><img src="https://user-images.githubusercontent.com/56767/56467671-fdd6ea80-645c-11e9-9056-a5d3fd5739e6.png" width="130" /></a></p>

# annict-kt
📚 [Annict API](https://github.com/annict/annict) wrapper for Kotlin Library.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.21-blue)](https://kotlinlang.org)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/iamtakagi/annict-kt)](https://github.com/iamtakagi/annict-kt/releases)
[![Publish to GitHub Package](https://github.com/iamtakagi/annict-kt/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/iamtakagi/annict-kt/actions/workflows/gradle-publish.yml)
[![license](https://img.shields.io/github/license/iamtakagi/annict-kt)](https://github.com/iam-takagi/kannict/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/iamtakagi/annict-kt)](https://github.com/iam-takagi/kannict/issues)
[![pull requests](https://img.shields.io/github/issues-pr/iamtakagi/annict-kt)](https://github.com/iamtakagi/kannict/pulls)

https://annict.jp/userland/projects/61

## Document
See a [this page](https://iamtakagi.github.io/annict-kt/kdoc/annict-kt)

## Installation

### Gradle

#### build.gradle.kts
```kotlin
dependencies {
    implementation("jp.annict:annict-kt:$AnnictKtVersion")
}
```

### Maven

#### pom.xml
```xml
<dependency>
  <groupId>jp.annict</groupId>
  <artifactId>annict-kt</artifactId>
  <version>${annictKtVersion}</version>
</dependency>
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

## Logo
The copyright of the logo belongs to [annict/annict-logo](https://github.com/annict/annict-logo).

## LICENSE
iamtakagi/annict-kt is provided under the MIT license.
