package com.example.aws.src.main.search

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model.ItemResponse

interface ItemCategoryFragmentView {
    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}