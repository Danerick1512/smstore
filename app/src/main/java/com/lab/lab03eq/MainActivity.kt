package com.lab.lab03eq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lab.lab03eq.ui.theme.Lab03EqTheme // 1. Tu tema actual (Material Theme Builder)
import com.lab.lab03eq.ui.navigation.AppNavigation // 2. Tu controlador de pantallas
import com.lab.lab03eq.ui.viewmodel.StoreViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite que la app se dibuje detrás de la barra de estado superior
        setContent {
            val viewModel: StoreViewModel = viewModel()
            val darkMode by viewModel.darkMode.collectAsState()

            // 3. Llamamos a tu tema personalizado
            Lab03EqTheme(darkTheme = darkMode, dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // 4. Esto usa el fondo exacto que definiste en el Theme Builder
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 5. REMPLAZO CLAVE: Eliminamos el Scaffold y Greeting por defecto
                    // y cargamos la navegación oficial de SanMarcosStore
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}