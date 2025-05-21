package com.example.lafyuu.repostory

import androidx.lifecycle.LiveData
import com.example.lafyuu.api.ProductServices
import com.example.lafyuu.model.BasketItems
import com.example.lafyuu.model.BookmarkItems
import com.example.lafyuu.model.Category
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.model.ProductModelItem
import com.example.lafyuu.room.BasketDAO
import com.example.lafyuu.room.BookmarkDAO
import com.example.lafyuu.room.ProductsDAO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepostory @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val apiServices:ProductServices,
    val productsDAO: ProductsDAO,
    val bookmarkDAO: BookmarkDAO,
    val basketDAO: BasketDAO
) {
  suspend  fun login(email:String,password:String)=
        firebaseAuth.signInWithEmailAndPassword(email,password).await()

    suspend fun register(email:String,password:String)=
        firebaseAuth.createUserWithEmailAndPassword(email,password).await()


    suspend fun getProduct()=apiServices.getProduct()

    suspend fun getCategory()=apiServices.getCategory()


    //product
    suspend fun insertProduct(product: ProductItems)=productsDAO.insertProduct(product)
     fun getAllProducts(): LiveData<List<ProductItems>> = productsDAO.getAllProducts()
    suspend fun deleteProducts(id:Int)=productsDAO.deleteProduct(id)

    //bookmark
    suspend fun insertBookmark(bookmark: BookmarkItems) = bookmarkDAO.insert(bookmark)
    fun getAllBookmarks(): LiveData<List<BookmarkItems>> = bookmarkDAO.getAll()
    suspend fun deleteBookmark(id: Int) = bookmarkDAO.delete(id)

    //basket
    suspend fun insertBasket(basket: BasketItems) = basketDAO.insert(basket)
    fun getAllBaskets(): LiveData<List<BasketItems>> = basketDAO.getAll()
    suspend fun deleteBasket(id: Int) = basketDAO.delete(id)














}

