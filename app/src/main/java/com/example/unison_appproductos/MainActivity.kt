package com.example.unison_appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unison_appproductos.Navigation.NavManager
import com.example.unison_appproductos.ViewModels.ProductoViewModel
import com.example.unison_appproductos.ui.theme.Unison_AppProductosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unison_AppProductosTheme {
                val productoViewModel: ProductoViewModel = viewModel()

                NavManager(viewModel = productoViewModel)
            }
        }
    }
}
