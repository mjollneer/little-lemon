package com.coursera.ll2.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MenuNetGetter {
    private val url =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

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
        val response: MenuData = client.get(url).body()
        return response
    }
}
