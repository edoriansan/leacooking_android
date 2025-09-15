package com.android.leacooking.data.model.custom

import androidx.room.DatabaseView

@DatabaseView("""
    SELECT 
        ri.id_recipe, 
        i.id AS id_ingredient, 
        i.ingredient_label,
        ri.quantity
    FROM recipe_ingredient ri
    JOIN ingredient i ON ri.id_ingredient = i.id
""")
data class IngredientWithQuantity(
    val id_recipe: Long,
    val id_ingredient: Long,
    val ingredient_label: String,
    val quantity: String?
)
