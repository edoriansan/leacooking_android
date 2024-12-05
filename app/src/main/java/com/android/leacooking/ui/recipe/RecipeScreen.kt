package com.android.leacooking.ui.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.android.leacooking.ui.recipe.components.RecipePart
import com.android.leacooking.ui.recipes.isLandscape
import com.android.leacooking.ui.theme.customFontFamily
import kotlin.collections.chunked
import kotlin.collections.forEach

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    recipeId: Long,
    viewModel: RecipeViewModel = hiltViewModel(),
) {
    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
        /* viewModel.loadRecipeParts(recipeId) */
    }

    val recipe by viewModel.recipe.collectAsState()
    val recipeParts by viewModel.recipeParts.collectAsState()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 2 else 1

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = recipe.imageUrl, // URL de l'image de la recette
                    contentDescription = "Image de la recette",
                    modifier = Modifier
                        .weight(1f) // L'image prend 50% de l'écran
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .weight(1f) // Titre et étapes à droite
                        .fillMaxHeight()
                ) {
                    Text(
                        text = recipe.title,
                        fontFamily = customFontFamily,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(recipeParts) { recipePart ->
                            RecipePart(recipePart = recipePart)
                        }
                    }
                }
            }
        } else {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = "Image de la recette",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = recipe.title,
                fontFamily = customFontFamily,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).background(Color.White).fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(recipeParts.chunked(columns)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowItems.forEach { recipePart ->
                            RecipePart(recipePart = recipePart)
                        }
                    }
                }
            }
        }
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
