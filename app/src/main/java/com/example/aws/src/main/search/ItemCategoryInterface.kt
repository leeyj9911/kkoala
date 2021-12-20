package com.example.aws.src.main.search

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.src.main.home.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemCategoryInterface {
    @GET("TYPE1_1/{TYPE1_1}")
    fun getItem(@Path("TYPE1_1")ITEM_ID: Int):retrofit2.Call<ItemCategoryResponse>
}