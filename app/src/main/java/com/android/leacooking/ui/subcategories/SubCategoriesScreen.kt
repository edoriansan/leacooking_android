package com.android.leacooking.ui.subcategories

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.leacooking.ui.planning.components.ImageCard
import com.android.leacooking.ui.planning.viewmodel.SubCategoriesViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SubCategoriesScreen(
    modifier: Modifier = Modifier,
    categoryLabel: String,
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    LaunchedEffect(categoryLabel) {
        viewModel.loadSubCategories(categoryLabel)
    }

    val subCategories by viewModel.subCategories.collectAsState()

    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val rows = if (isLandscape) 3 else 4
        val cardSize: Dp = maxHeight / rows

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            subCategories.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { subCategory ->
                        ImageCard(
                            imageUrl = subCategory.subCategoryImg,
                            label = subCategory.subCategoryLabel,
                            modifier = Modifier
                                .size(cardSize)
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SubCategoriesScreen(categoryLabel = "Salé")
}

@Composable
fun isLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
