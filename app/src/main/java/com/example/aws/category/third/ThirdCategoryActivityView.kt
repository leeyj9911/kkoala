package com.example.aws.category.third

import com.example.aws.category.model2.ItemCategoryResponse

interface ThirdCategoryActivityView {
    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}