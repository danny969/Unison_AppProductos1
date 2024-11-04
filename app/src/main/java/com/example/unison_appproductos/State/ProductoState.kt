package com.example.unison_appproductos.State

import com.example.unison_appproductos.model.Producto

data class ProductoState(
    val productos: List<Producto> = listOf(),
    val estaCargando: Boolean = true
)
