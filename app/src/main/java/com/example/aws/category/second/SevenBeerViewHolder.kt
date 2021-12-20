package com.example.aws.category.second

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.SecondCategoryItemBinding

class SevenBeerViewHolder(val context: SecondActivity, private val binding: SecondCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item: SevenBeerItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.keyword
        binding.itemId.text = item.ITEM_ID
        binding.keword1.text = item.keyword1
        binding.keword2.text = item.keyword2

    }
}