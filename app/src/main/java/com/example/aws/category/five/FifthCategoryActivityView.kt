package com.example.aws.category.five

import com.example.aws.category.model2.ItemCategoryResponse

interface FifthCategoryActivityView {

    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}