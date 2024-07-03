package com.coursera.ll2.network

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(val menu: Array<MenuItemData>) {
}