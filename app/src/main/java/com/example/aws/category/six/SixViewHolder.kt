package com.example.aws.category.six

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.category.model.CategoryResponse
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.databinding.SixCategoryItemBinding
import com.example.aws.databinding.ThirdCategoryItemBinding
import retrofit2.Callback

class SixViewHolder(val context: SixActivity, private val binding: SixCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item:SixItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.keyword

    }
}