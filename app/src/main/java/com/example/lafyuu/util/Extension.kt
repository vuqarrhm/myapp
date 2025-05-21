package com.example.lafyuu.util

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url:String){
    Picasso.get().load(url).into(this);
}

fun View.visibleItem(){
    this.visibility=View.VISIBLE
}
fun View.goneItem(){
    this.visibility=View.GONE
}
