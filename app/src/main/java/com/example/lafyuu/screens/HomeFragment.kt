package com.example.lafyuu.screens

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lafyuu.adapter.BasketAdapter
import com.example.lafyuu.adapter.BookmarkAdapter
import com.example.lafyuu.adapter.CategoryAdapter
import com.example.lafyuu.adapter.ClickType
import com.example.lafyuu.adapter.ProductAdapter
import com.example.lafyuu.databinding.FragmentHomeBinding
import com.example.lafyuu.model.BasketItems
import com.example.lafyuu.model.BookmarkItems
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val basketViewModel: BasketViewModel by viewModels()
    private val categoryAdapter = CategoryAdapter()
    private lateinit var productAdapter: ProductAdapter
    private var basketList = listOf<BasketItems>()  // Basketdə olan məhsulların siyahısı
    private var bookmarkList= listOf<BookmarkItems>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        categoryAdapter.onClickItem = { categoryIdString ->
            val categoryId = categoryIdString.toIntOrNull()
            categoryId?.let {
                viewModel.filterProductsByCategory(it)
            }
        }

        binding.rv2.adapter = categoryAdapter

        basketViewModel.allBaskets().observe(viewLifecycleOwner) { basketItems ->
            basketList = basketItems
        }

        bookmarkViewModel.bookmarks().observe(viewLifecycleOwner){ bookmarkitems->
            bookmarkList=bookmarkitems
        }


        productAdapter = ProductAdapter { item, clickType ->
            when (clickType) {
                ClickType.BOOKMARK -> {
                    val alreadyExists=bookmarkList.any { it.title==item.title }
                    if (!alreadyExists){
                        val bookmarkItem = BookmarkItems(
                            title = item.title,
                            description = item.description,
                            updatedAt = item.updatedAt,
                            images = item.images,
                            price = item.price
                        )
                        bookmarkViewModel.addBookmark(bookmarkItem)
                    }

                }

                ClickType.BASKET -> {
                    val alreadyExists = basketList.any { it.title == item.title }

                    if (!alreadyExists) {
                        val basketItem = BasketItems(
                            title = item.title,
                            description = item.description,
                            updatedAt = item.updatedAt,
                            images = item.images,
                            price = item.price
                        )
                        basketViewModel.addToBasket(basketItem)
                    }

                }
            }
        }

        binding.RV.adapter = productAdapter
        observeData()
    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner) { data ->
            data?.let {
                productAdapter.updateList(it)
            }
        }

        viewModel.categoryList.observe(viewLifecycleOwner) { data ->
            categoryAdapter.updateList(data)
        }
    }
}
