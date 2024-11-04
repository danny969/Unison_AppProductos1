package com.example.unison_appproductos.Views

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unison_appproductos.ViewModels.ProductoViewModel
import com.example.unison_appproductos.dialogs.ProductFormDialog
import com.example.unison_appproductos.model.Producto
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormView(viewModel: ProductoViewModel, navController: NavController, productoId: Int? = null) {
    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaRegistro by remember { mutableStateOf("") }

    LaunchedEffect(productoId) {
        productoId?.let {
            val producto = viewModel.getProductById(it)
            producto?.let {
                nombre = it.nombre
                precio = it.precio
                descripcion = it.descripcion
                fechaRegistro = it.fechaRegistro
            }
        }
    }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, selectedYear, selectedMonth, selectedDay ->
            fechaRegistro = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (productoId != null) "Modificar Producto" else "Nuevo Producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver a Lista de Productos")
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(Color(0xFF697565))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val backgroundColor = Color(0xFF697565)
            val fieldBackgroundColor = Color(0xFFECDFCC)
            val buttonColor = Color(0xFF3C3D37)
            val titleColor = Color(0xFF1E201E)

            Text(
                text = "Producto",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 50.sp, color = titleColor)
            )
            Spacer(modifier = Modifier.height(50.dp))

            BasicTextField(
                value = nombre,
                onValueChange = { nombre = it },
                textStyle = TextStyle(fontSize = 26.sp),
                decorationBox = { innerTextField ->
                    Column {
                        Text(text = "Nombre", style = TextStyle(fontSize = 26.sp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(fieldBackgroundColor)
                                .padding(8.dp)
                        ) { innerTextField() }
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            BasicTextField(
                value = precio,
                onValueChange = { precio = it },
                textStyle = TextStyle(fontSize = 26.sp),
                decorationBox = { innerTextField ->
                    Column {
                        Text(text = "Precio", style = TextStyle(fontSize = 26.sp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(fieldBackgroundColor)
                                .padding(8.dp)
                        ) { innerTextField() }
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            BasicTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                textStyle = TextStyle(fontSize = 26.sp),
                decorationBox = { innerTextField ->
                    Column {
                        Text(text = "Descripción", style = TextStyle(fontSize = 26.sp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(fieldBackgroundColor)
                                .padding(8.dp)
                        ) { innerTextField() }
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            // Fecha de Registro con DatePickerDialog
            Column {
                Text(text = "Fecha de Registro", style = TextStyle(fontSize = 26.sp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(fieldBackgroundColor)
                        .padding(8.dp)
                        .clickable { datePickerDialog.show() },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = if (fechaRegistro.isEmpty()) "Selecciona una fecha" else fechaRegistro,
                        style = TextStyle(fontSize = 26.sp, color = Color.Black)
                    )
                }
            }
            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.size(width = 170.dp, height = 70.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = Color.White)
                ) {
                    Text(text = "Registrar", style = TextStyle(fontSize = 26.sp))
                }

                Button(
                    onClick = {
                        nombre = ""
                        descripcion = ""
                        precio = ""
                        fechaRegistro = ""

                        navController.navigateUp()
                    },
                    modifier = Modifier.size(width = 170.dp, height = 70.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = Color.White)
                ) {
                    Text(text = "Cancelar", style = TextStyle(fontSize = 26.sp))
                }
            }

            ProductFormDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = {
                    val producto = Producto(
                        id = productoId ?: 0,
                        nombre = nombre,
                        descripcion = descripcion,
                        precio = precio,
                        fechaRegistro = fechaRegistro
                    )
                    if (productoId != null) {
                        viewModel.update(producto)
                    } else {
                        viewModel.insert(producto)
                    }
                    navController.navigateUp()
                },
                productoId = productoId
            )

        }
    }
}
