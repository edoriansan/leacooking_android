package com.android.leacooking.ui.shared.topBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.shadow(8.dp),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(0.dp, 0.dp, 18.dp, 0.dp), // temporary
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://wallpapers.com/images/hd/chocolate-chip-cookie-illustration-ydqooj1l5qsz6cid.jpg", // URL de votre logo
                    contentDescription = "Logo LeaCooking",
                    modifier = Modifier.height(40.dp)
                )
            }
        }
    )
}
