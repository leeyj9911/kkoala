package com.example.aws

import com.example.aws.personal.PersonalResultResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonalInterface {
    @GET("input_itemId/{input_itemId}")
    fun getItem(@Path("input_itemId") input_itemId: String?):retrofit2.Call<PersonalResultResponse>
}