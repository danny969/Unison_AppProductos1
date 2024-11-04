package com.example.unison_appproductos.dialogs

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ProductFormDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    productoId: Int? = null
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = if (productoId != null) "Confirmar Modificación" else "Confirmar Registro") },
            text = { Text(if (productoId != null) "¿Desea modificar el producto?" else "¿Desea registrar el producto?") },
            confirmButton = {
                Button(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        )
    }
}
