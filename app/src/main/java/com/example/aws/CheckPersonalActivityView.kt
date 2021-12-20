package com.example.aws

import com.example.aws.src.main.home.model2.PersonalResponse

interface CheckPersonalActivityView {
    fun onGetItemSuccess(response: PersonalResponse)
    fun onGetItemFailurse(message:String)
}