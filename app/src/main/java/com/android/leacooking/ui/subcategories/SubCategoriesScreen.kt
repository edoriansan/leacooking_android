package com.android.leacooking.ui.subcategories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard
import com.android.leacooking.ui.utils.isLandscape

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

    val subCategories by viewModel.subCategories.collectAsStateWithLifecycle()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2
    val padding = 6.dp

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val cardHeight: Dp = maxHeight / (if (isLandscape) 3 else 4)
        val cardWidth: Dp = (maxWidth - padding * (columns - 1)) / columns

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            items(subCategories.chunked(columns)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(padding)
                ) {
                    rowItems.forEach { subCategory ->
                        ImageCard(
                            imageUrl = subCategory.recipeSubcategoryImg,
                            label = subCategory.recipeSubcategoryLabel,
                            modifier = Modifier
                                .weight(1f)
                                .height(cardHeight)
                                .padding(3.dp)
                                .clickable {
                                    navController.navigate("${Screen.RECIPES.route}/${subCategory.id}")
                                }
                        )
                    }

                    if (rowItems.size < columns) {
                        repeat(columns - rowItems.size) {
                            Spacer(modifier = Modifier.width(cardWidth))
                        }
                    }
                }
            }
        }
    }
}
