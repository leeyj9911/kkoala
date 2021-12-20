package com.example.aws.personal

data class Item(
    val input_itemId: InputItemId,
    val output_recommendedItems_001: OutputRecommendedItems001,
    val output_recommendedItems_002: OutputRecommendedItems002,
    val output_recommendedItems_003: OutputRecommendedItems003
)