# annict-kt

Stage: Development

## Example

### Authorization

#### Get Token
```kotlin
val response: TokenGetResponseData = AnnictAuth().token(TokenGetRequestData("client_id", "client_secret", "authorization_code"(default), "urn:ietf:wg:oauth:2.0:oob"(default), "code"))

val access_token = response.access_token
```

#### Get Info
```kotlin
val response: TokenInfoGetResponseData = AnnictAuth().info("access_token")
```

#### Revoke Token
```kotlin
val result: Boolean = AnnictAuth().revoke(RevokeTokenPostRequestData("client_id", "client_secret", "token"))
```

### Service

```kotlin
val client = AnnictClient("access_token")

val response = client.getWorks(WorksRequestQuery(filter_title="氷菓"))
```
