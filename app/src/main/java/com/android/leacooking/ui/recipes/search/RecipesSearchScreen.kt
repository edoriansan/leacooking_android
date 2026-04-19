package com.android.leacooking.ui.recipes.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
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

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val cardSize = screenHeight / (if (isLandscape) 3 else 4)

    if (recipes.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(recipes.chunked(columns)) { rowItems ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    rowItems.forEach { recipe ->
                        ImageCard(
                            imageUrl = recipe.imageUrl ?: "",
                            label = recipe.title,
                            modifier = Modifier
                                .weight(1f)
                                .height(cardSize)
                                .padding(6.dp)
                                .clickable {
                                    navController.navigate("${Screen.RECIPE.route}/${recipe.id}")
                                }
                        )
                    }
                    repeat(columns - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    } else {
        Image(
            painter = painterResource(id = R.drawable.inouche),
            contentDescription = "Vide",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }
}