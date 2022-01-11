package com.example.shopinglist.domain

class AddItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addItem(ShopItem: ShopItem){
        shopListRepository.addItem(ShopItem)
    }
}