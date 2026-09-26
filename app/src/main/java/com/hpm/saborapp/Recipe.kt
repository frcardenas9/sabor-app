package com.hpm.saborapp

data class Recipe(
    val id: Int,
    val name: String,
    val category: String,
    val preparationTime: Int,
    val servings: Int,
    val imageResId: Int,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>
)
