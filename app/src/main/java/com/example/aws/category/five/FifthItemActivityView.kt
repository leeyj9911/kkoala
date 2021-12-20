package com.example.aws.category.five

import com.example.aws.category.ITEM.ItemPostResponse

interface FifthItemActivityView {
    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message: String)

}