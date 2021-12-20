package com.example.aws.category.four

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.config.ApplicationClass
import com.example.aws.src.main.search.ItemCategoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FourthCategoryService(val view: FourthCategoryActivityView) {
    fun tryGetItem(TYPE1_1:Int){
        val HomeInterface = ApplicationClass.sRetrofit.create(ItemCategoryInterface::class.java)
        HomeInterface.getItem(TYPE1_1).enqueue(object : Callback<ItemCategoryResponse> {
            override fun onResponse(call: Call<ItemCategoryResponse>, response: Response<ItemCategoryResponse>) {
                view.onGetItemSuccess(response.body() as ItemCategoryResponse)
            }

            override fun onFailure(call: Call<ItemCategoryResponse>, t: Throwable) {
                view.onGetItemFailure(t.message?:"통신오류")
            }
        })
    }
}