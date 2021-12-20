package com.example.aws.category.six

import com.example.aws.category.model2.ItemCategoryResponse

interface SixCategoryActivityview {

    fun onGetItemSuccess(response: ItemCategoryResponse)
    fun onGetItemFailure(message:String)
}