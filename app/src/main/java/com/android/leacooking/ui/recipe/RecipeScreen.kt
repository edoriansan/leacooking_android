package com.android.leacooking.ui.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
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

    val recipe by viewModel.recipe.collectAsStateWithLifecycle()
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
                SubcomposeAsyncImage(
                    model = recipe.recipe.recipeImg,
                    contentDescription = "Image de la recette",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    when (painter.state) {
                        is coil.compose.AsyncImagePainter.State.Error -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            )
                        }
                        else -> SubcomposeAsyncImageContent()
                    }
                }

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
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = recipe.recipe.persons.toString(),
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person Icon"
                            )
                        }
                    }

                    item {
                        TitleWithLine(text = "Ingrédients")
                    }
                    items(recipe.parts) { recipePart ->
                        Ingredients(recipePart = recipePart)
                    }

                    item {
                        TitleWithLine(text = "Étapes")
                    }
                    items(recipe.parts) { recipePart ->
                        RecipePart(recipePart = recipePart)
                    }
                }
            }
        } else {
            SubcomposeAsyncImage(
                model = recipe.recipe.recipeImg,
                contentDescription = "Image de la recette",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (painter.state) {
                    is coil.compose.AsyncImagePainter.State.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                        )
                    }
                    else -> SubcomposeAsyncImageContent()
                }
            }

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
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = recipe.recipe.persons.toString(),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person Icon"
                        )
                    }
                }

                item {
                    TitleWithLine(text = "Ingrédients")
                }
                items(recipe.parts) { recipePart ->
                    Ingredients(recipePart = recipePart)
                }

                item {
                    TitleWithLine(text = "Étapes")
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

@Composable
fun TitleWithLine(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontFamily = customFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(1.dp),
            color = Color.Gray
        )
    }
}

@Composable
fun isLandscape(): Boolean {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
