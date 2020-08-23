# annict-kt

Stage: Development

## Example

### Authorization

```kotlin
// Get Token (アクセストークンを取得する)

val response: TokenGetResponseData = AnnictAuth().token(TokenGetRequestData("client_id", "client_secret", "authorization_code"(default), "urn:ietf:wg:oauth:2.0:oob"(default), "code"))

val access_token = response.access_token
```

```kotlin
// Get Info (認証ユーザの情報を取得する)

val response: TokenInfoGetResponseData = AnnictAuth().info("access_token")
```

```kotlin
// Revoke Token (トークンを失効させる)

val result: Boolean = AnnictAuth().revoke(RevokeTokenPostRequestData("client_id", "client_secret", "token"))
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
```

```kotlin
// キャスト情報取得

val response = client.getCasts(filter_work_id = 1808, per_page = 5, page = 5)
```
