package com.example.aws.category.first

import android.util.Log
import com.example.aws.category.ITEM.ItemPostResponse
import com.example.aws.category.ITEM.PostItem
import com.example.aws.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemService(val view: ItemActivityView){
    fun tryPostSign(postItem: PostItem){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(ItemInterface::class.java)
        homeRetrofitInterface.getItemId(postItem).enqueue(object :
            Callback<ItemPostResponse> {
            override fun onResponse(call: Call<ItemPostResponse>, response: Response<ItemPostResponse>) {

                view.onPostItemIDSuccess(response.body() as ItemPostResponse)
            }

            override fun onFailure(call: Call<ItemPostResponse>, t: Throwable) {
                view.onPostItemIdFailure(t.message ?:"통신 오류")
            }
        })
    }
}