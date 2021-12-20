package com.example.aws

import com.example.aws.config.ApplicationClass
import com.example.aws.personal.PersonalResultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalService(val view: PersonalActivityView) {
    fun tryGetItem(input_itemId: String?){
        val HomeInterface = ApplicationClass.sRetrofit.create(PersonalInterface::class.java)
        HomeInterface.getItem(input_itemId).enqueue(object : Callback<PersonalResultResponse> {
            override fun onResponse(call: Call<PersonalResultResponse>, response: Response<PersonalResultResponse>) {
                view.onGetItemSuccess(response.body() as PersonalResultResponse)
            }

            override fun onFailure(call: Call<PersonalResultResponse>, t: Throwable) {
                view.onGetItemFailure(t.message?:"통신오류")
            }
        })
    }
}