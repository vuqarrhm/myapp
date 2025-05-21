package com.example.lafyuu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lafyuu.R
import com.example.lafyuu.databinding.ItemBasketBinding
import com.example.lafyuu.model.BasketItems
import com.example.lafyuu.model.ProductItems
import com.example.lafyuu.util.loadImage
import com.squareup.picasso.Picasso
import java.math.BigDecimal

class BasketAdapter(
    private var onClickItems:(BasketItems)->Unit,
    private var onQuantityChanged: () -> Unit

):RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    val list= arrayListOf<BasketItems>()

    inner class BasketViewHolder(val itemBasketBinding: ItemBasketBinding):RecyclerView.ViewHolder(itemBasketBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layout=ItemBasketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket=list[position]
        holder.itemBasketBinding.basket=basket

        basket.images?.let {
            Picasso.get().load(it[0]).into(holder.itemBasketBinding.imageView12)
        }


        holder.itemBasketBinding.imageViewPlus.setOnClickListener {
            basket.quantity++
            holder.itemBasketBinding.textViewSay.text = basket.quantity.toString()
            onQuantityChanged()
        }

        holder.itemBasketBinding.imageViewMinus.setOnClickListener {
            if (basket.quantity > 1) {
                basket.quantity--
                holder.itemBasketBinding.textViewSay.text = basket.quantity.toString()
                onQuantityChanged()
            }
        }


        holder.itemBasketBinding.imageViewZibil.setOnClickListener {
            onClickItems(basket)
        }


    }

    fun calculateTotalPrice(): BigDecimal {
        var total = BigDecimal.ZERO
        for (item in list) {
            val price = item.price ?: BigDecimal.ZERO
            total = total.add(price.multiply(BigDecimal.valueOf(item.quantity.toLong())))
        }
        return total
    }



    fun updataList(newList:List<BasketItems>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }

}