# annict-kt

未完成

## Example
```kotlin
val client = AnnictLib.newClient("token here", new OkHttpClient(), AnnictVersion.V_1)

val res = CastsService(client).get(CastsRequestQuery(arrayOf("id", "name"), null, null, 10, 10, null,null))

println(res)
```
