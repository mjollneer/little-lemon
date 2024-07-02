package com.nulana.littlelemon.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class MenuNetGetter {
    private val url =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    private val client = HttpClient() {}

//    {
//        menu: [
//        {
//                id: int,
//                title: string,
//                description: string,
//                price: int,
//                image: string,
//                category: string,
//        },
//        ...
//        ]
//    }
suspend fun getAsText(): String {
    val response = client.get(url)
    return response.bodyAsText()
}

suspend fun getAsDataObj(): MenuData {
    val response = client.get(url)
    return response.body()
}
}
