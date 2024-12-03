package com.android.leacooking.ui.recipes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.leacooking.ui.shared.imageCard.ImageCard

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    subCategoryLabel: String,
    /*navController: NavController,*/
    viewModel: RecipesViewModel = hiltViewModel()
) {
    LaunchedEffect(subCategoryLabel) {
        viewModel.loadRecipes(subCategoryLabel)
    }

    val recipes by viewModel.recipes.collectAsState()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val cardSize: Dp = maxHeight / (if (isLandscape) 3 else 4)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recipes.chunked(columns)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    rowItems.forEach { recipe ->
                        ImageCard(
                            imageUrl = recipe.imageUrl,
                            label = recipe.title,
                            modifier = Modifier
                                .weight(1f)
                                .height(cardSize)
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun isLandscape(): Boolean {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
