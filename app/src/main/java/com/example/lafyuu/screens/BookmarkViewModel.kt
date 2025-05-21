package com.example.lafyuu.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu.model.BookmarkItems
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.repostory.ProductRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: ProductRepostory
) : ViewModel() {

    fun  bookmarks(): LiveData<List<BookmarkItems>> = repository.getAllBookmarks()



    fun addBookmark(item: BookmarkItems) {
        viewModelScope.launch {
            repository.insertBookmark(item)
        }
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            repository.deleteBookmark(id)
        }
    }
}

