package com.example.movilesbiblioteca

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

enum class Pantalla {
    INICIO, CATALOGO, DETALLE, FORMULARIO
}

@Composable
fun LibroApp(viewModel: LibroViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.pantallaActual) {
        Pantalla.INICIO -> {
            LibroHomeScreen {
                viewModel.navegarA(Pantalla.CATALOGO)
            }
        }

        Pantalla.CATALOGO -> {
            LibroCatalogScreen(
                libros = uiState.listaLibros,
                onLibroClick = { libro ->
                    viewModel.seleccionarLibro(libro)
                },
                onBack = {
                    viewModel.volverAlInicio()
                },
                onAddClick = {
                    viewModel.navegarA(Pantalla.FORMULARIO)
                }
            )
        }

        Pantalla.DETALLE -> {
            uiState.libroSeleccionado?.let { libro ->
                LibroDetailScreen(
                    libro = libro,
                    onBack = {
                        viewModel.volverALaLista()
                    }
                )
            }
        }

        Pantalla.FORMULARIO -> {
            LibroFormScreen(
                onLibroAdded = { nuevoLibro ->
                    viewModel.agregarLibro(nuevoLibro)
                    viewModel.volverALaLista()
                },
                onBack = {
                    viewModel.volverALaLista()
                }
            )
        }
    }
}
