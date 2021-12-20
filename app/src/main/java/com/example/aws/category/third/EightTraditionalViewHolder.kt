package com.example.aws.category.third

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.ThirdCategoryItemBinding

class EightTraditionalViewHolder(val context: ThirdActivity, private val binding: ThirdCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item:EightTraditionalItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.keyword
        binding.itemId.text = item.ITEM_ID
        binding.keword1.text = item.keword1
        binding.keword2.text = item.keyword2

    }
}
