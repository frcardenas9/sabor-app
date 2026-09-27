package com.hpm.saborapp

enum class IngredientUnit {
    GRAMS,
    UNITS,
    MILLILITERS,
    TO_TASTE
}

data class RecipeIngredient(
    val name: String,
    val amountPerServing: Double?,
    val unit: IngredientUnit
)
