package com.example.unison_appproductos.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unison_appproductos.Navigation.NavDestinations
import com.example.unison_appproductos.R

@Composable
fun HomeView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF697565)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = "Logo de la Empresa",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 50.dp)
        )

        Text(
            text = "FastMart",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFECDFCC),
            modifier = Modifier.padding(top = 16.dp)
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 50.dp)
        ) {
            Button(
                onClick = { navController.navigate(NavDestinations.ProductList) },
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3C3D37),
                    contentColor = Color(0xFFECDFCC)
                )

            ) {
                Text("Productos")
            }

            Button(
                onClick = { navController.navigate(NavDestinations.PresentationCard) },
                modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3C3D37),
                    contentColor = Color(0xFFECDFCC)
                )
            ) {
                Text("Presentación")
            }
        }

        Text(
            text = "Lopez Nuñez Luis Daniel",
            fontSize = 20.sp,
            color = Color(0xFFECDFCC),
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

