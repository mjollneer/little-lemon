package com.nulana.littlelemon.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json

class MenuGetter {
    private val url =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
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
}
