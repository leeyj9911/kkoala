package com.example.aws

import com.example.aws.config.ApplicationClass
import com.example.aws.src.main.home.HomePersonalFragmentView
import com.example.aws.src.main.home.HomePersonalInterface
import com.example.aws.src.main.home.model2.PersonalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckPersonalService(val view: CheckPersonalActivityView) {
    fun tryGetItem(ALL_ITEM:Int){
        val HomeInterface = ApplicationClass.sRetrofit.create(CheckPersonalInterface::class.java)
        HomeInterface.getItem(ALL_ITEM).enqueue(object : Callback<PersonalResponse> {
            override fun onResponse(call: Call<PersonalResponse>, response: Response<PersonalResponse>) {
                view.onGetItemSuccess(response.body() as PersonalResponse)
            }

            override fun onFailure(call: Call<PersonalResponse>, t: Throwable) {
                view.onGetItemFailurse(t.message?:"통신오류")
            }
        })
    }
}