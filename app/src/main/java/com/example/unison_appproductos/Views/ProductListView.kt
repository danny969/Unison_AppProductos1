package com.example.unison_appproductos.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unison_appproductos.ViewModels.ProductoViewModel
import com.example.unison_appproductos.Navigation.NavDestinations
import com.example.unison_appproductos.dialogs.DeleteConfirmationDialog
import com.example.unison_appproductos.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListView(viewModel: ProductoViewModel, modifier: Modifier = Modifier, navController: NavController) {
    var showDeleteDialog by remember { mutableStateOf<Producto?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var showNoProductsDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver a HomeView")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavDestinations.ProductForm) },
                content = { Icon(Icons.Default.Add, contentDescription = "Añadir Producto") }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(Color(0xFF697565))
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar producto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    disabledTextColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Gray)
            )

            val estado = viewModel.estado.collectAsState().value
            val filteredProducts = estado.productos.filter {
                it.nombre.contains(searchQuery, ignoreCase = true)
            }

            if (estado.estaCargando) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF697565))
                }
            } else {
                LaunchedEffect(filteredProducts.isEmpty() && searchQuery.isNotEmpty()) {
                    if (filteredProducts.isEmpty() && searchQuery.isNotEmpty()) {
                        showNoProductsDialog = true
                    }
                }

                LazyColumn {
                    items(filteredProducts) { producto ->
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF3C3D37), shape = MaterialTheme.shapes.medium)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "${producto.nombre} ($${producto.precio} MXN)",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFECDFCC)
                            )
                            Text(
                                text = producto.descripcion,
                                color = Color(0xFFECDFCC),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Fecha de creación: ${producto.fechaRegistro}",
                                color = Color(0xFFECDFCC),
                                fontSize = 12.sp
                            )

                            Row {
                                IconButton(
                                    onClick = {
                                        navController.navigate("${NavDestinations.ProductForm}/${producto.id}")
                                    }
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Modificar", tint = Color(0xFFECDFCC))
                                }

                                IconButton(
                                    onClick = { showDeleteDialog = producto }
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color(0xFFECDFCC))
                                }
                            }
                        }
                    }
                }

                showDeleteDialog?.let { producto ->
                    DeleteConfirmationDialog(
                        onDismiss = { showDeleteDialog = null },
                        onConfirm = {
                            viewModel.delete(producto)
                            showDeleteDialog = null
                        },
                        productoNombre = producto.nombre
                    )
                }
            }

            if (showNoProductsDialog) {
                AlertDialog(
                    onDismissRequest = { showNoProductsDialog = false },
                    title = { Text("Advertencia") },
                    text = { Text("No hay ningún producto, agrega uno") },
                    confirmButton = {
                        TextButton(onClick = { showNoProductsDialog = false }) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}
