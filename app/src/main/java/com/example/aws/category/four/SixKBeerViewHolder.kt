package com.example.aws.category.four

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.FourthCategoryItemBinding

class SixKBeerViewHolder(val context: FourthActivity, private val binding: FourthCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item:SixKBeerItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.keyword
        binding.itemId.text =item.ITEM_ID
        binding.keword1.text = item.keyword1
        binding.keword2.text = item.keyword2
    }
}