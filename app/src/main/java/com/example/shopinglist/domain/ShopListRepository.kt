package com.example.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addItem(ShopItem: ShopItem)

    fun deleteItem(ShopItem: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}