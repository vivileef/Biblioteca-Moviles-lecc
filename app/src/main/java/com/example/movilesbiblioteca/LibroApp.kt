package com.example.movilesbiblioteca

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

enum class Pantalla {
    INICIO, CATALOGO, DETALLE
}

@Composable
fun LibroApp() {
    val pantallaActual = rememberSaveable { mutableStateOf(Pantalla.INICIO) }
    val libroSeleccionado = remember { mutableStateOf<Libro?>(null) }

    when (pantallaActual.value) {
        Pantalla.INICIO -> {
            LibroHomeScreen {
                pantallaActual.value = Pantalla.CATALOGO
            }
        }

        Pantalla.CATALOGO -> {
            LibroCatalogScreen(
                libros = listaLibrosDummy,
                onLibroClick = { libro ->
                    libroSeleccionado.value = libro
                    pantallaActual.value = Pantalla.DETALLE
                },
            ) {
                pantallaActual.value = Pantalla.INICIO
            }
        }

        Pantalla.DETALLE -> {
            libroSeleccionado.value?.let { libro ->
                LibroDetailScreen(libro = libro) {
                    pantallaActual.value = Pantalla.CATALOGO
                }
            }
        }
    }
}
