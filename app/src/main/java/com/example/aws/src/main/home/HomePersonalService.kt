package com.example.aws.src.main.home

import com.example.aws.category.model2.ItemCategoryResponse
import com.example.aws.category.second.SecondCategoryActivityView
import com.example.aws.config.ApplicationClass
import com.example.aws.src.main.home.model2.PersonalResponse
import com.example.aws.src.main.search.ItemCategoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePersonalService(val view: HomePersonalFragmentView) {
    fun tryGetItem(ALL_ITEM:Int){
        val HomeInterface = ApplicationClass.sRetrofit.create(HomePersonalInterface::class.java)
        HomeInterface.getItem(ALL_ITEM).enqueue(object : Callback<PersonalResponse> {
            override fun onResponse(call: Call<PersonalResponse>, response: Response<PersonalResponse>) {
                view.onGetItemSuccess(response.body() as PersonalResponse)
            }

            override fun onFailure(call: Call<PersonalResponse>, t: Throwable) {
                view.onGetItemFailure(t.message?:"통신오류")
            }
        })
    }
}