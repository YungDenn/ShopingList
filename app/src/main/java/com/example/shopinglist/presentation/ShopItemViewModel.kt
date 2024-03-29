package com.example.shopinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen


    fun getShopItem(ShopItemId: Int){
        val item = getItemUseCase.getShopItem(ShopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsIsValid = validateInput(name, count)
        if (fieldsIsValid){
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addItem(shopItem)
            finishWork()
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsIsValid = validateInput(name, count)
        if (fieldsIsValid){
            _shopItem.value?.let {// Если объект не равен null
                val item =it.copy(name = name, count = count)
                editItemUseCase.editItem(item)
                finishWork()
            }

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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0){
            _errorInputCount.value = true
            result = false
        }
        return  result
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }

    fun resetErrorInputCount(){
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _closeScreen.value = Unit
    }
}