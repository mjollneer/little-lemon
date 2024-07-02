package com.nulana.littlelemon.network

import com.nulana.littlelemon.DB.MenuItem
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemData(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val image: String,
    val category: String
) {
    fun getDBItem(): MenuItem {
        return MenuItem(id, title, description, price, image, category)
    }
}



