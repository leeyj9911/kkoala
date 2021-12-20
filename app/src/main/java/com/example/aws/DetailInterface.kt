package com.example.aws

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailInterface {
    @GET("ITEM_ID_1/{ITEM_ID_1}")
    fun getItem(@Path("ITEM_ID_1")ITEM_ID_1: String):retrofit2.Call<ItemResponse>
}