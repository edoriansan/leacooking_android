package com.android.leacooking.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.home.components.CatLoadingSpinner
import com.android.leacooking.ui.shared.imageCard.ImageCard
import com.android.leacooking.ui.utils.isLandscape

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 2 else 1
    val showSyncProgressDialog by viewModel.showSyncProgressDialog.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.synchronisation()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            categories.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { category ->
                        ImageCard(
                            imageUrl = category.recipeCategoryImg,
                            label = category.recipeCategoryLabel,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate("${Screen.SUBCATEGORIES.route}/${category.id}")
                                },
                            fontSize = 40.sp
                        )
                    }
                    repeat(columns - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        if (showSyncProgressDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CatLoadingSpinner(
                    modifier = Modifier.height(200.dp)
                )
            }
        }
    }
}