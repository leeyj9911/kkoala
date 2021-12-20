package com.example.aws.src.main.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.FragementHomeItemBinding


class HomeViewHolder(val context: HomeFragment, private val binding: FragementHomeItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item: HomePersonalItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.keyword
        binding.ITEMID.text = item.ITEM_ID
    }
}