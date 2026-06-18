package com.example.movilesbiblioteca

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroCatalogScreen(
    libros: List<Libro>,
    onLibroClick: (Libro) -> Unit,
    onBack: () -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Explora Libros") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Libro")
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(all = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding),
        ) {
            items(items = libros) { libro ->
                LibroGridItem(
                    libro = libro,
                    onClick = { onLibroClick(libro) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibroCatalogScreenPreview() {
    LibroCatalogScreen(
        libros = listaLibrosDummy,
        onLibroClick = { },
        onBack = { },
        onAddClick = { },
    )
}
