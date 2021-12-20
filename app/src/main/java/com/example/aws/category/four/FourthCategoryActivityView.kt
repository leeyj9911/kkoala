package com.example.aws.category.four

import com.example.aws.category.model2.ItemCategoryResponse

interface FourthCategoryActivityView {
    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}