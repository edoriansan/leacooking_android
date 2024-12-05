package com.android.leacooking.ui.shared.imageCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.android.leacooking.ui.subcategories.SubCategoriesScreen
import com.android.leacooking.ui.theme.customFontFamily

@Composable
fun ImageCard(imageUrl: String, label: String, modifier: Modifier, fontSize: TextUnit = 20.sp) {
    Card(
        modifier = modifier.shadow(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            ) {
                when (painter.state) {
                    is coil.compose.AsyncImagePainter.State.Loading -> {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                    is coil.compose.AsyncImagePainter.State.Error -> {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(Color.Red),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Erreur", color = Color.White)
                        }
                    }
                    else -> SubcomposeAsyncImageContent()
                }
            }

            // Superposition du texte
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp), // Padding autour du texte
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label.uppercase(),
                    color = Color.White,
                    fontSize = fontSize,
                    fontFamily = customFontFamily
                )
            }
        }
    }
}
