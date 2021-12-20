package com.example.aws.category.first

import com.example.aws.category.ITEM.ItemPostResponse

interface ItemActivityView {
    fun onPostItemIDSuccess(response: ItemPostResponse)
    fun onPostItemIdFailure(message:String)
}