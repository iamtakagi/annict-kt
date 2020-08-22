# annict-kt

Stage: Development

## Example

### Authorization

#### Get Token 
```kotlin
val response = AnnictAuth().token(TokenGetRequestData("client_id", "client_secret", "authorization_code", "urn:ietf:wg:oauth:2.0:oob", "code"))

val access_token = response.access_token
```

#### Get Info
```kotlin
val response = AnnictAuth().info("access_token")
```

#### Revoke Token
```kotlin
AnnictAuth().revoke(RevokeTokenPostRequestData("client_id","client_secret","token"))
```

### Service

```kotlin
val client = AnnictClient("access_token")

val response = client.getWorks(WorksRequestQuery(filter_title="氷菓"))
```
