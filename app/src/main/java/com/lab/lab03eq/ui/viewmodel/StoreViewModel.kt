package com.lab.lab03eq.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lab.lab03eq.data.SettingsManager
import com.lab.lab03eq.model.Producto
import com.lab.lab03eq.model.productosDePrueba
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsManager = SettingsManager(application)

    val darkMode: StateFlow<Boolean> = settingsManager.darkModeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val favorites: StateFlow<Set<String>> = settingsManager.favoritesFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptySet()
    )

    val productos: StateFlow<List<Producto>> = favorites.map { favSet ->
        productosDePrueba.map { producto ->
            producto.copy(favorito = favSet.contains(producto.id.toString()))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = productosDePrueba
    )

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsManager.setDarkMode(enabled)
        }
    }

    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            settingsManager.toggleFavorite(productId)
        }
    }

    fun getProductoById(id: Int): Producto? {
        return productos.value.find { it.id == id }
    }
}
