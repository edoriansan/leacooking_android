package com.android.leacooking.data.model.custom

import androidx.room.DatabaseView

@DatabaseView("""
    SELECT 
        rpi.id_recipe_part, 
        i.id AS id_ingredient, 
        i.ingredient_label, 
        i.id_quantity_type, 
        qt.label AS quantity_type_label, 
        rpi.quantity
    FROM recipe_part_ingredient rpi
    JOIN ingredient i ON rpi.id_ingredient = i.id
    JOIN quantity_type qt ON i.id_quantity_type = qt.id
""")
data class IngredientWithQuantity(
    val id_recipe_part: Long,
    val id_ingredient: Long,
    val ingredient_label: String,
    val id_quantity_type: Long?,
    val quantity_type_label: String?,
    val quantity: Int
)
