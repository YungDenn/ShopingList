package com.example.shopinglist.domain

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteItem(ShopItem: ShopItem){
        shopListRepository.deleteItem(ShopItem)
    }
}