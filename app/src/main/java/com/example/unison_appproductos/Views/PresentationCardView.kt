package com.example.unison_appproductos.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unison_appproductos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationCardView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Presentación") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver a HomeView")
                    }
                }
            )
        }
    ) { contentPadding ->
        val backgroundColor = Color(0xFF697565)
        val textColor = Color(0xFFECDFCC)

        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = "Foto Formal",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Luis Danie López Núñez", fontSize = 26.sp, color = textColor, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Desarrollador Web", fontSize = 20.sp, color = textColor, modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                ContactInfoRow(imageRes = R.drawable.phone_icon, contactText = "662-259-2100")
                Spacer(modifier = Modifier.height(10.dp))
                ContactInfoRow(imageRes = R.drawable.social_icon, contactText = "@danny969")
                Spacer(modifier = Modifier.height(10.dp))
                ContactInfoRow(imageRes = R.drawable.mail_icon, contactText = "a220212744@unison.mx")
            }
        }
    }
}

@Composable
fun ContactInfoRow(imageRes: Int, contactText: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Icono de contacto",
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(Color.White)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = contactText, fontSize = 16.sp, color = Color.White)
    }
}
