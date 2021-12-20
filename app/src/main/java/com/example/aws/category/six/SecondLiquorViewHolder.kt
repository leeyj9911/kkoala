package com.example.aws.category.six

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.SixCategoryItemBinding

class SecondLiquorViewHolder(val context: SixActivity, private val binding: SixCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item:SecondliquorItemData){
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