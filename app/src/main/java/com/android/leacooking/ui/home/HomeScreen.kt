package com.android.leacooking.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard
import com.android.leacooking.ui.home.viewmodel.HomeViewModel
import com.android.leacooking.ui.recipes.isLandscape
import kotlin.collections.chunked

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

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val cardHeight: Dp = maxHeight / (if (isLandscape) 1 else 2)

        LaunchedEffect(Unit) {
            viewModel.synchronisation()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showSyncProgressDialog) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            categories.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { category ->
                        ImageCard(
                            imageUrl = category.recipeCategoryImg,
                            label = category.recipeCategoryLabel,
                            modifier = Modifier
                                .weight(1f)
                                .height(cardHeight)
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
    }
}
