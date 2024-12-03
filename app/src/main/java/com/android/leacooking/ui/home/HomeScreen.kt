package com.android.leacooking.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard
import com.android.leacooking.ui.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val categories by viewModel.categories.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categories.forEach { category ->
            ImageCard(
                imageUrl = category.categoryImg,
                label = category.categoryLabel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {
                        navController.navigate("${Screen.SUBCATEGORIES.route}/${category.categoryLabel}")
                    },
                fontSize = 40.sp
            )
        }
    }
}