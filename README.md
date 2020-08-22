# annict-kt

## Authorization 
```kotlin
```

## Service

```kotlin
val client = AnnictLib.newClient("token here")

val res = client.getWorks(WorksRequestQuery(filter_title="氷菓"))

println(res)
```
