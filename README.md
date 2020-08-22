# annict-kt

Stage: Development

## Example

### Authorization

- Get Token 
```kotlin
val response = AnnictAuth().token(TokenGetRequestData("client_id",　"client_secret",　"authorization_code",　"urn:ietf:wg:oauth:2.0:oob","code"))

val token = response.access_token
```

### Service

```kotlin
val client = AnnictClient("token here")

val response = client.getWorks(WorksRequestQuery(filter_title="氷菓"))
```
