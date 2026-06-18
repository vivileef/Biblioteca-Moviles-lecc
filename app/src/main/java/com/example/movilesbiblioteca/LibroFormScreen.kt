package com.example.movilesbiblioteca

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroFormScreen(
    onLibroAdded: (Libro) -> Unit,
    onBack: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Libro") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = autor,
                onValueChange = { autor = it },
                label = { Text("Autor") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                label = { Text("Año de Publicación") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (titulo.isNotBlank() && autor.isNotBlank()) {
                        onLibroAdded(
                            Libro(
                                id = (0..1000).random(),
                                titulo = titulo,
                                autor = autor,
                                genero = genero,
                                anio = anio.toIntOrNull() ?: 2024,
                                sinopsis = "Sinopsis generada automáticamente.",
                                puntuacion = 5.0f,
                                resenas = 0
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titulo.isNotBlank() && autor.isNotBlank()
            ) {
                Text("Guardar Libro")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibroFormScreenPreview() {
    LibroFormScreen(onLibroAdded = {}, onBack = {})
}
