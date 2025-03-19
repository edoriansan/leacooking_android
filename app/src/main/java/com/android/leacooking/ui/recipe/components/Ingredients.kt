package com.android.leacooking.ui.recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.leacooking.data.model.custom.RecipePartWithIngredients
import com.android.leacooking.ui.theme.customFontFamily
import androidx.compose.material3.Text

@Composable
fun Ingredients(recipePart: RecipePartWithIngredients) {
    if (recipePart.ingredients.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(32.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                recipePart.ingredients.forEach { ingredient ->
                    Text(
                        text = "${ingredient.quantity ?: ""} ${ingredient.ingredient_label}",
                        fontSize = 18.sp,
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
