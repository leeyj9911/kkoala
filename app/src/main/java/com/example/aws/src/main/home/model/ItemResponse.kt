package com.example.aws.src.main.home.model

data class ItemResponse(
    val Count: Int,
    val Items: List<Item>,
    val ScannedCount: Int
)