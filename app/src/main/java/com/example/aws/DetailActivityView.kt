package com.example.aws

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model.ItemResponse

interface DetailActivityView {
    fun onGetItemSuccess(response: ItemResponse)
    fun onGetItemFailure(message:String)
}