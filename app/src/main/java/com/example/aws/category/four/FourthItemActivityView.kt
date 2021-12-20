package com.example.aws.category.four

import com.example.aws.category.ITEM.ItemPostResponse

interface FourthItemActivityView {
    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message: String)

}