# annict-kt

## Authorization 
```kotlin
```

## Service

```kotlin
val client = AnnictLib.newClient("token here", AnnictVersion.V_1)

val res = WorksService(client).get(WorksRequestQuery(filter_title="氷菓"))

println(res)
```
