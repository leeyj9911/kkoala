package com.example.aws.category.first

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.CategoryItemBinding

class FirstViewHolder(val context: Context, private val binding:CategoryItemBinding):RecyclerView.ViewHolder(binding.root) {

    fun view(item:FirstItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.review
    }
}