package com.hpm.saborapp

object PortionCalculator {

    fun calculate(
        ingredient: RecipeIngredient,
        portions: Int
    ): Double? {

        if (ingredient.amountPerServing == null) {
            return null
        }

        return ingredient.amountPerServing * portions
    }

    fun formatAmount(
        ingredient: RecipeIngredient,
        portions: Int,
        showGrams: Boolean
    ): String {

        val amount = calculate(
            ingredient,
            portions
        )

        if (amount == null) {
            return "Al gusto"
        }

        return when (ingredient.unit) {

            IngredientUnit.GRAMS -> {

                if (showGrams) {
                    formatNumber(amount) + " g"
                } else {
                    formatNumber(amount / 1000.0) + " kg"
                }
            }

            IngredientUnit.UNITS -> {

                formatNumber(amount) + " unidades"
            }

            IngredientUnit.MILLILITERS -> {

                formatNumber(amount) + " ml"
            }

            IngredientUnit.TO_TASTE -> {
                "Al gusto"
            }
        }
    }

    private fun formatNumber(
        value: Double
    ): String {

        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            String.format(
                java.util.Locale.US,
                "%.2f",
                value
            )
        }
    }
}