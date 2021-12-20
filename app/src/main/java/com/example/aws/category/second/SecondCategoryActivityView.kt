package com.example.aws.category.second

import com.example.aws.category.model2.ItemCategoryResponse

interface SecondCategoryActivityView {
    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}