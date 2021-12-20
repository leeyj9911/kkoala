package com.example.aws.category.model2

data class ItemCategoryResponse(
    val Count: Int,
    val Items: List<Item>,
    val ScannedCount: Int
)