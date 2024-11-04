package com.example.unison_appproductos.dialogs


import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun DeleteConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    productoNombre: String
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Confirmar Eliminación") },
        text = { Text("¿Desea eliminar el producto $productoNombre?") },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}
