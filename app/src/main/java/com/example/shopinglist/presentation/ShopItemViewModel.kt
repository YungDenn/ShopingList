package com.example.shopinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shopinglist.data.ShopListRepositorylmpl
import com.example.shopinglist.domain.AddItemUseCase
import com.example.shopinglist.domain.EditItemUseCase
import com.example.shopinglist.domain.GetItemByIdUseCase
import com.example.shopinglist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {
    private val repository = ShopListRepositorylmpl

    private val getItemUseCase = GetItemByIdUseCase(repository)
    private val addShopItemUseCase = AddItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    fun getShopItem(ShopItemId: Int){
        val item = getItemUseCase.getShopItem(ShopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsIsValid = validateInput(name, count)
        if (fieldsIsValid){
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addItem(shopItem)
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsIsValid = validateInput(name, count)
        if (fieldsIsValid){
            val shopItem = ShopItem(name, count, true)
            editItemUseCase.editItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
        // Если inputName не равен null, тогда обрезать пробелы (.trim())
        // иначе вернуть пустую строку ""
    }

    private fun parseCount(inputCount: String?) : Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }
    private fun validateInput(name: String, count: Int): Boolean{
        var result = true
        if (name.isBlank()){
            result = false
        }
        if (count <= 0){
            result = false
        }
        return  result
    }

}