package com.example.aws.category.five

import com.example.aws.category.model2.ItemCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FifthCategoryInterface {
    @GET("TYPE1_1/{TYPE1_1}")
    fun getItem(@Path("TYPE1_1")ITEM_ID: Int):retrofit2.Call<ItemCategoryResponse>
}