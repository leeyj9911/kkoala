package com.example.aws.category.third

import com.example.aws.category.ITEM.ItemPostResponse

interface ThirdItemActivityView {
    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message: String)

}