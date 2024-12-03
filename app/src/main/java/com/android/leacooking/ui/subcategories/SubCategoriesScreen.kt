package com.android.leacooking.ui.subcategories

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.leacooking.ui.planning.components.ImageCard
import com.android.leacooking.ui.planning.viewmodel.SubCategoriesViewModel
import com.android.leacooking.data.models.SubCategory

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SubCategoriesScreen(
    modifier: Modifier = Modifier,
    categoryLabel: String, // Recevoir l'argument de catégorie
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    // Charger les sous-catégories en fonction de la catégorie sélectionnée
    LaunchedEffect(categoryLabel) {
        viewModel.loadSubCategories(categoryLabel)
    }

    val subCategories by viewModel.subCategories.collectAsState()

    // Vérification de l'orientation
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2 // Plus de colonnes en mode paysage

    // Utilisation de BoxWithConstraints pour ajuster la taille des cartes
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val rows = if (isLandscape) 3 else 4 // Plus de lignes en mode portrait
        val cardSize: Dp = maxHeight / rows // Taille dynamique des cartes

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns), // Colonnes adaptées à l'orientation
            modifier = Modifier.fillMaxSize()
        ) {
            items(subCategories) { subCategory ->
                ImageCard(
                    imageUrl = subCategory.subCategoryImg,
                    label = subCategory.subCategoryLabel,
                    modifier = Modifier
                        .padding(8.dp) // Padding entre les éléments
                        .size(cardSize) // Taille des cartes dynamiques
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodListScreen() {
    // Assurer une catégorie de test pour le preview
    SubCategoriesScreen(categoryLabel = "Salé")
}

@Composable
fun isLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
