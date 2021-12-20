package com.example.aws.category.first

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aws.databinding.CatecoryFirstItemBinding
import com.example.aws.databinding.CategorySojuFifthItemBinding

class FifthSojuViewHolder(val context: Context, private val binding: CategorySojuFifthItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun view(item:FifthSojuItemData){
        Glide.with(binding.itemIm).load(item.itemim).into(binding.itemIm)
        binding.itemName.text = item.name
        binding.itemPrice.text = item.price
        binding.itemSt.text = item.star
        binding.itemKeyword.text = item.review
        binding.itemId.text = item.ITEM_ID
        binding.keword1.text = item.keyword1
        binding.keword2.text = item.keyword2


    }
}