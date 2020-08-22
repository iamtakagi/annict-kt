# annict-kt

Stage: Development

## Example

### Authorization

#### Get Token - TokenGetResponseData
```kotlin
val response = AnnictAuth().token(TokenGetRequestData("client_id", "client_secret", "authorization_code"(default), "urn:ietf:wg:oauth:2.0:oob"(default), "code"))

val access_token = response.access_token
```

#### Get Info - TokenInfoGetResponseData
```kotlin
val response = AnnictAuth().info("access_token")
```

#### Revoke Token - Boolean
```kotlin
val result = AnnictAuth().revoke(RevokeTokenPostRequestData("client_id", "client_secret", "token"))
```

### Service

```kotlin
val client = AnnictClient("access_token")

val response = client.getWorks(WorksRequestQuery(filter_title="氷菓"))
```
