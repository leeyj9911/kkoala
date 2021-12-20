package com.example.aws.category.second

import com.example.aws.category.ITEM.ItemPostResponse

interface SecondItemActivityView {

    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message: String)

}