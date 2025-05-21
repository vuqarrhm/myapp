package com.example.lafyuu.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu.model.BasketItems
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.repostory.ProductRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BasketViewModel @Inject constructor(
    val repostory: ProductRepostory
):ViewModel() {

    fun allBaskets(): LiveData<List<BasketItems>> = repostory.getAllBaskets()
    private val _totalPriceLiveData = MutableLiveData<Double>()
    val totalPriceLiveData: LiveData<Double> = _totalPriceLiveData

    fun addToBasket(basketItems: BasketItems) {
        viewModelScope.launch(Dispatchers.IO) {
            repostory.insertBasket(basketItems)
        }
    }

    fun deleteBasket(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repostory.deleteBasket(id)
        }
    }

    fun updateTotalPrice(totalPrice:Double,discount:Double){
        val discountPrice=totalPrice*(1-discount)
        _totalPriceLiveData.value=Math.round(discountPrice).toDouble()

    }


}