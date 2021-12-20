package com.example.aws

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.personal.PersonalResultResponse

interface PersonalActivityView {
    fun onGetItemSuccess(response: PersonalResultResponse)
    fun onGetItemFailure(message:String)
}