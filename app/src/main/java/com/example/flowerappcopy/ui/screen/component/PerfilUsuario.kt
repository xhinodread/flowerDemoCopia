package com.example.flowerappcopy.ui.screen.component

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.example.flowerappcopy.ui.theme.ghost_white

@Composable
fun  PerfilUsuario() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Perfil de Usuario")
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ4D3xt5q3rk7jjEA1l9wx7nooKnF-hrgy8Vwmg9gRqDIdacN8Os3dhhCvaaKxsOccMHw&usqp=CAU",
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
        /***/
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            value = "Nombre",
            onValueChange = {},
            placeholder = { "" },
            label = { "" },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Yellow),
            readOnly = true
        )
        /****/
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {Text("Input")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Yellow),
            readOnly = true
        )

    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyContent(){
    var context = LocalContext.current
    // Declare a string that contains a url
    val mUrl = "https://elquisoft.cl/ligaadef/liga_tabla.asp"

    // Adding a WebView inside AndroidView
    // with layout as full screen

    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                   // ViewGroup.MarginLayoutParams.MATCH_PARENT ,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                //setInitialScale(99)
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                loadUrl(mUrl)
            }
        },
        update = {
            it.loadUrl(mUrl)
        },
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Red)
           // .width(IntrinsicSize.Max)
    )
}
