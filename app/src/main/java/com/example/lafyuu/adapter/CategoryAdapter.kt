package com.example.lafyuu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lafyuu.databinding.ItemCategoryBinding
import com.example.lafyuu.model.Category
import com.example.lafyuu.util.loadImage

class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val list= arrayListOf<Category>()
    lateinit var onClickItem:(String)->Unit

    inner class CategoryViewHolder(val itemCategoryBinding: ItemCategoryBinding):RecyclerView.ViewHolder(itemCategoryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=list[position]
        holder.itemCategoryBinding.category=category

        holder.itemCategoryBinding.root.setOnClickListener {
            onClickItem(category.id.toString())
        }
        category.image?.let {
            holder.itemCategoryBinding.imageView4.loadImage(it)
        }



    }

    fun updateList(newList:List<Category>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}