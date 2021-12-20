package com.example.aws.category.first

import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import retrofit2.http.Body
import retrofit2.http.POST

interface ItemInterface {
    @POST("ITEM_ID")
    fun getItemId(@Body params : PostItem):retrofit2.Call<ItemPostResponse>

}