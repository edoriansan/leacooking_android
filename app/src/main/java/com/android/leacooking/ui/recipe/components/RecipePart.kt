package com.android.leacooking.ui.recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.leacooking.data.model.custom.RecipeWithIngredients
import com.android.leacooking.ui.theme.customFontFamily

@Composable
fun RecipePart(recipePart: RecipeWithIngredients) {
    Column(
        modifier = Modifier
            .padding(32.dp, 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = recipePart.part.recipePartProcess,
            fontFamily = customFontFamily,
            fontSize = 16.sp
        )
    }
}
