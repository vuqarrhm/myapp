package com.example.lafyuu.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lafyuu.R
import com.example.lafyuu.databinding.ItemProductBinding
import com.example.lafyuu.model.BookmarkItems
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.model.ProductModelItem
import com.example.lafyuu.screens.HomeFragment
import com.example.lafyuu.screens.HomeFragmentDirections
import com.example.lafyuu.util.loadImage
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val onItemClick: (ProductItems,ClickType) -> Unit  // Bookmark klik funksiyası

): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val list = arrayListOf<ProductModelItem>()

    inner class ProductViewHolder(val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(layout)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.itemProductBinding.product = product

        product.images?.let {
            if (it.isNotEmpty()) {
                Picasso.get().load(it[0]).into(holder.itemProductBinding.imageView2)
            }
        }

        holder.itemProductBinding.root.setOnClickListener {
            Navigation.findNavController(it).navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(product)
            )
        }


        var isClicked = false

        // Bookmark düyməsinə klik
        holder.itemProductBinding.imageView13.setOnClickListener {
            val bookmarkedItem = ProductItems(
                title = product.title ?: "No Title",
                description = product.description,
                updatedAt = product.updatedAt,
                images = product.images,
                price = product.price
            )
            onItemClick(bookmarkedItem,ClickType.BOOKMARK)


            val btn = holder.itemProductBinding.imageView13
            if (isClicked) {
                btn.setBackgroundResource(R.drawable.img_19)
            } else {
                btn.setBackgroundResource(R.drawable.bookmark)
            }
            isClicked = !isClicked


        }

        holder.itemProductBinding.imageViewBasket.setOnClickListener {
            val basketItem = ProductItems(
                title = product.title ?: "No Title",
                description = product.description,
                updatedAt = product.updatedAt,
                images = product.images,
                price = product.price

            )
            onItemClick(basketItem,ClickType.BASKET) // Bookmark-a məlumat ötürülür


        }


        holder.itemProductBinding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(holder.itemView.context,"Rating $rating",Toast.LENGTH_SHORT).show()
        }


    }
    fun updateList(newList: List<ProductModelItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
}}
