package com.android.leacooking.ui.recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.leacooking.data.model.custom.RecipePartWithIngredients
import com.android.leacooking.ui.theme.customFontFamily

@Composable
fun RecipePart(recipePart: RecipePartWithIngredients) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = recipePart.part.recipePartTitle,
            fontSize = 20.sp,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = recipePart.part.recipePartProcess,
            fontFamily = customFontFamily,
            fontSize = 16.sp
        )
    }
}
