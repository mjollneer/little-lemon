package com.nulana.littlelemon.network

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(val menu: Array<MenuItemData>) {
}