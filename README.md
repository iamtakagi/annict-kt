# annict-kt

Stage: Development

## Example

### Authorization

```kotlin
//Get Token

val response: TokenGetResponseData = AnnictAuth().token(TokenGetRequestData("client_id", "client_secret", "authorization_code"(default), "urn:ietf:wg:oauth:2.0:oob"(default), "code"))

val access_token = response.access_token
```

```kotlin
//Get Info

val response: TokenInfoGetResponseData = AnnictAuth().info("access_token")
```

```kotlin
//Revoke Token

val result: Boolean = AnnictAuth().revoke(RevokeTokenPostRequestData("client_id", "client_secret", "token"))
```

### Service

```kotlin
val client = AnnictClient("access_token")

val response = client.getWorks(WorksRequestQuery(filter_title="氷菓"))
```
