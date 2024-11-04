package com.example.unison_appproductos.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unison_appproductos.Views.HomeView
import com.example.unison_appproductos.Views.ProductFormView
import com.example.unison_appproductos.Views.ProductListView
import com.example.unison_appproductos.Views.PresentationCardView
import com.example.unison_appproductos.ViewModels.ProductoViewModel

@Composable
fun NavManager(viewModel: ProductoViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestinations.Home
    ) {
        composable(NavDestinations.Home) {
            HomeView(navController = navController)
        }

        composable(NavDestinations.ProductList) {
            ProductListView(viewModel = viewModel, navController = navController)
        }

        composable("${NavDestinations.ProductForm}/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            ProductFormView(viewModel = viewModel, navController = navController, productoId = productoId)
        }
        composable(NavDestinations.ProductForm) {
            ProductFormView(viewModel = viewModel, navController = navController)
        }


        composable(NavDestinations.PresentationCard) {
            PresentationCardView(navController = navController)
        }
    }
}
