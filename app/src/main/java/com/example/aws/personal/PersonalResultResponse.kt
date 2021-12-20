package com.example.aws.personal

data class PersonalResultResponse(
    val Count: Int,
    val Items: List<Item>,
    val ScannedCount: Int
)