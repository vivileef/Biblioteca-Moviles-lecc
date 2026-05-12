package com.example.movilesbiblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.movilesbiblioteca.ui.theme.MovilesBibliotecaTheme

class LibroMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovilesBibliotecaTheme {
                LibroApp()
            }
        }
    }
}
