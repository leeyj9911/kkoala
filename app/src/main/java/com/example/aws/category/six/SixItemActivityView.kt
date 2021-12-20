package com.example.aws.category.six

import com.example.aws.category.ITEM.ItemPostResponse

interface SixItemActivityView {
    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message: String)

}