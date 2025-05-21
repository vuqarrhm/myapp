package com.example.lafyuu.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu.api.ProductServices
import com.example.lafyuu.model.Category
import com.example.lafyuu.model.ProductModelItem
import com.example.lafyuu.repostory.ProductRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val productRepostory: ProductRepostory
):ViewModel() {
  val productList=MutableLiveData<List<ProductModelItem>>()
  val categoryList=MutableLiveData<List<Category>>()
  val error=MutableLiveData<String>()

    init {
        category()
        product()

    }

    fun product(){
        viewModelScope.launch {
            try {
                val response=productRepostory.getProduct()
                if (response.isSuccessful){
                    response.body()?.let {
                        productList.value=it
                    }
                }

            }catch (e:Exception){
                error.value=e.localizedMessage

            }
        }
    }

    fun category(){
        viewModelScope.launch {
            try {
                val response=productRepostory.getCategory()
                if (response.isSuccessful){
                    response.body()?.let {
                        categoryList.value=it

                    }
                }

            }catch (e:Exception){

            }
        }
    }

    fun filterProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                val response = productRepostory.getProduct() // Bütün məhsulları alın
                if (response.isSuccessful) {
                    response.body()?.let { allProducts ->
                        val filteredProducts = allProducts.filter { it.category?.id == categoryId }
                        productList.value = filteredProducts
                    }
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }



}