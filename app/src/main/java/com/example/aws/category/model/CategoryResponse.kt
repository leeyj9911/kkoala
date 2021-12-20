package com.example.aws.category.model

data class CategoryResponse(
    val Count: Int,
    val Items: List<Item>,
    val ScannedCount: Int
)