package com.android.leacooking.ui.shared.topBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearchClick: () -> Unit,
    onHomeClick: () -> Unit,
    canGoBack: Boolean,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.shadow(8.dp),
        navigationIcon = {
            if (canGoBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Retour"
                    )
                }
            }
        },
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(30.dp, 0.dp, 0.dp, 0.dp), // temp
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { onHomeClick() }) {
                    AsyncImage(
                        model = "https://wallpapers.com/images/hd/chocolate-chip-cookie-illustration-ydqooj1l5qsz6cid.jpg", // URL de votre logo
                        contentDescription = "Logo LeaCooking",
                        modifier = Modifier.height(40.dp)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Rechercher"
                )
            }
        }
    )
}

@Composable
fun SearchDialog(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    onSearch: (String) -> Unit
) {
    if (isOpen) {
        var query by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Rechercher") },
            text = {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Ingrédient, titre, recette...") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSearch(query)
                        onDismiss()
                    }
                ) {
                    Text("Rechercher")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Annuler")
                }
            }
        )
    }
}
