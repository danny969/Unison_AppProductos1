package com.example.unison_appproductos.ViewModels

import androidx.lifecycle.viewModelScope
import com.example.unison_appproductos.model.Producto
import kotlinx.coroutines.launch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.unison_appproductos.State.ProductoState
import com.example.unison_appproductos.room.ProductDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao = ProductDatabase.getInstance(application).productDatabaseDao()
    private val _estado = MutableStateFlow(ProductoState())
    val estado: StateFlow<ProductoState> = _estado

    init {
        viewModelScope.launch {
            _estado.value = _estado.value.copy(estaCargando = true)
            productDao.getAllProducts().collect { productos ->
                _estado.value = ProductoState(productos = productos, estaCargando = false)
            }
        }
    }
    val allProducts = productDao.getAllProducts()

    fun insert(producto: Producto) {
        viewModelScope.launch {
            productDao.insert(producto)
        }
    }

    fun update(producto: Producto) {
        viewModelScope.launch {
            productDao.update(producto)
        }
    }

    fun delete(producto: Producto) {
        viewModelScope.launch {
            productDao.delete(producto)
        }
    }

    fun getProductById(id: Int): Producto? {
        return estado.value.productos.find { it.id == id }
    }

}
