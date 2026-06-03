package com.lab.lab03eq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lab.lab03eq.ui.theme.Lab03EqTheme // 1. Tu tema actual (Material Theme Builder)
import com.lab.lab03eq.ui.navigation.AppNavigation // 2. Tu controlador de pantallas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite que la app se dibuje detrás de la barra de estado superior
        setContent {
            // 3. Llamamos a tu tema personalizado
            Lab03EqTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // 4. Esto usa el fondo exacto que definiste en el Theme Builder
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 5. REMPLAZO CLAVE: Eliminamos el Scaffold y Greeting por defecto
                    // y cargamos la navegación oficial de SanMarcosStore
                    AppNavigation()
                }
            }
        }
    }
}