package com.android.leacooking.ui.subcategories

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SubCategoriesScreen(
    modifier: Modifier = Modifier,
    categoryId: Long,
    navController: NavController,
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    LaunchedEffect(categoryId) {
        viewModel.loadSubCategories(categoryId)
    }

    val subCategories by viewModel.subCategories.collectAsState()

    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val rows = if (isLandscape) 3 else 4
        val cardHeight: Dp = maxHeight / rows
        val cardWidth: Dp = maxWidth / columns

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            subCategories.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    rowItems.forEach { recipeSubcategory ->
                        ImageCard(
                            imageUrl = recipeSubcategory.recipeSubcategoryImg,
                            label = recipeSubcategory.recipeSubcategoryLabel,
                            modifier = Modifier
                                .width(cardWidth)
                                .height(cardHeight)
                                .padding(4.dp)
                                .clickable {
                                    navController.navigate("${Screen.RECIPES.route}/${recipeSubcategory.id}")
                                },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun isLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
