package com.example.shopinglist.domain

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editItem(shopItem: ShopItem){
        shopListRepository.editItem(shopItem)
    }
}