package com.example.aws.src.main.home

import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model2.PersonalResponse

interface HomePersonalFragmentView {
    fun onGetItemSuccess(response: PersonalResponse)
    fun onGetItemFailure(message:String)
}