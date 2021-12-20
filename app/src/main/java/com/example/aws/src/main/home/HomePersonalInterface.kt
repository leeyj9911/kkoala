package com.example.aws.src.main.home

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model2.PersonalResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomePersonalInterface {
    @GET("ALL_ITEM/{ALL_ITEM}")
    fun getItem(@Path("ALL_ITEM")ALL_ITEM: Int):retrofit2.Call<PersonalResponse>
}