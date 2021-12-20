package com.example.aws

import com.example.aws.src.main.home.model2.PersonalResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CheckPersonalInterface {
    @GET("ALL_ITEM/{ALL_ITEM}")
    fun getItem(@Path("ALL_ITEM")ALL_ITEM: Int):retrofit2.Call<PersonalResponse>
}