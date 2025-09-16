package com.android.leacooking.ui.recipes.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.leacooking.R
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard
import com.android.leacooking.ui.utils.isLandscape

@Composable
fun RecipesSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    query: String,
    viewModel: RecipeSearchViewModel = hiltViewModel()
) {
    LaunchedEffect(query) {
        viewModel.searchRecipes(query)
    }

    val recipes by viewModel.searchResults.collectAsStateWithLifecycle()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val cardSize: Dp = maxHeight / (if (isLandscape) 3 else 4)

        if(recipes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                items(recipes.chunked(columns)) { rowItems ->
                    Row {
                        rowItems.forEach { recipe ->
                            recipe.imageUrl?.let {
                                ImageCard(
                                    imageUrl = it,
                                    label = recipe.title,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(cardSize)
                                        .padding(5.dp)
                                        .clickable {
                                            navController.navigate("${Screen.RECIPE.route}/${recipe.id}")
                                        },
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.inouche),
                contentDescription = "Vide",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
