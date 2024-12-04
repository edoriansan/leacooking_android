package com.android.leacooking.ui.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.leacooking.ui.shared.imageCard.ImageCard

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    recipeId: Long,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
    }

    val recipe by viewModel.recipe.collectAsState()
    val isLandscape = isLandscape()

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = recipe.title, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeScreen(recipeId = 1)
}


@Composable
fun isLandscape(): Boolean {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
