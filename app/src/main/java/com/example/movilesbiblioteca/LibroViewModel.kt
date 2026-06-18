package com.example.movilesbiblioteca

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LibroUiState(
    val listaLibros: List<Libro> = emptyList(),
    val libroSeleccionado: Libro? = null,
    val pantallaActual: Pantalla = Pantalla.INICIO
)

class LibroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LibroUiState())
    val uiState: StateFlow<LibroUiState> = _uiState.asStateFlow()

    init {
        // Cargar datos iniciales
        _uiState.update { it.copy(listaLibros = listaLibrosDummy) }
    }

    fun navegarA(pantalla: Pantalla) {
        _uiState.update { it.copy(pantallaActual = pantalla) }
    }

    fun seleccionarLibro(libro: Libro) {
        _uiState.update { 
            it.copy(
                libroSeleccionado = libro,
                pantallaActual = Pantalla.DETALLE
            )
        }
    }

    fun volverALaLista() {
        _uiState.update { it.copy(pantallaActual = Pantalla.CATALOGO) }
    }
    
    fun volverAlInicio() {
        _uiState.update { it.copy(pantallaActual = Pantalla.INICIO) }
    }

    fun agregarLibro(libro: Libro) {
        _uiState.update { 
            it.copy(listaLibros = it.listaLibros + libro)
        }
    }
}
