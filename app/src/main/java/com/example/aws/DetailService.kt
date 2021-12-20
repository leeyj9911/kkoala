package com.example.aws

import com.example.aws.config.ApplicationClass
import com.example.aws.src.main.home.model.ItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailService(val view: DetailActivityView) {
    fun tryGetItem(ITEM_ID_1: String?){
        val HomeInterface = ApplicationClass.sRetrofit.create(DetailInterface::class.java)
        if (ITEM_ID_1 != null) {
            HomeInterface.getItem(ITEM_ID_1).enqueue(object : Callback<ItemResponse> {
                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                    view.onGetItemSuccess(response.body() as ItemResponse)
                }

                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    view.onGetItemFailure(t.message?:"통신오류")
                }
            })
        }
    }
}