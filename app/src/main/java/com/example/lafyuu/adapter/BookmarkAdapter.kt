package com.example.lafyuu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lafyuu.databinding.FragmentBookmarkBinding
import com.example.lafyuu.databinding.ItemBookmarkBinding
import com.example.lafyuu.databinding.ItemProductBinding
import com.example.lafyuu.model.BookmarkItems
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.model.ProductModelItem
import com.squareup.picasso.Picasso

class BookmarkAdapter(
    private val onBookmarkClick: (BookmarkItems) -> Unit
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val list = arrayListOf<BookmarkItems>()

    inner class BookmarkViewHolder(val itemBookmarkBinding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(itemBookmarkBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val layout = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(layout)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = list[position]
        holder.itemBookmarkBinding.bookmark = bookmark
        bookmark.images?.let {
            Picasso.get().load(it[0]).into(holder.itemBookmarkBinding.imageView11)
        }



        holder.itemBookmarkBinding.bookmarkbtn.setOnClickListener {
            onBookmarkClick(bookmark)
        }

    }

    fun updateList(newList: List<BookmarkItems>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
