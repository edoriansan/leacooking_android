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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.android.leacooking.ui.recipe.components.Ingredients
import com.android.leacooking.ui.recipe.components.RecipePart
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
    }

    val recipe by viewModel.recipe.collectAsState()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 2 else 1

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = recipe.recipe.recipeImg,
                    contentDescription = "Image de la recette",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxHeight()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(
                            color = Color(125,65,65),
                            text = recipe.recipe.title,
                            fontFamily = customFontFamily,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(Color(251,194,181))
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Text(
                            text = "Ingrédients",
                            fontFamily = customFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(recipe.parts) { recipePart ->
                        Ingredients(recipePart = recipePart)
                    }

                    item {
                        Text(
                            text = "Étapes",
                            fontFamily = customFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(recipe.parts) { recipePart ->
                        RecipePart(recipePart = recipePart)
                    }
                }
            }
        } else {
            AsyncImage(
                model = recipe.recipe.recipeImg,
                contentDescription = "Image de la recette",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(3f).background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = recipe.recipe.title,
                        fontFamily = customFontFamily,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color(251,194,181))
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Text(
                        text = "Ingrédients",
                        fontFamily = customFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(recipe.parts) { recipePart ->
                    Ingredients(recipePart = recipePart)
                }

                item {
                    Text(
                        text = "Étapes",
                        fontFamily = customFontFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(recipe.parts.chunked(columns)) { rowItems ->
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
