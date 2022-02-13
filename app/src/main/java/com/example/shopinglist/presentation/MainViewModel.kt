package com.example.shopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglist.data.ShopListRepositorylmpl
import com.example.shopinglist.domain.DeleteItemUseCase
import com.example.shopinglist.domain.EditItemUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem

class MainViewModel: ViewModel() {


    private val repository = ShopListRepositorylmpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()



    fun deleteShopItem(ShopItem: ShopItem){
        deleteItemUseCase.deleteItem(ShopItem)

    }
    fun changeEnabledItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editItemUseCase.editItem(newItem)
    }
}