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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        ///Text("Perfil de Usuario")
        Text("ElquiSoft")

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(ghost_white)
                .padding(top = 10.dp),
        ) {

            val urlImages = listOf<String>(
                "https://elquisoft.cl/ligaadef/images/1.png",
                "https://elquisoft.cl/ligaadef/images/2.png",
                "https://elquisoft.cl/ligaadef/images/3.png",
                "https://elquisoft.cl/ligaadef/images/4.png",
                "https://elquisoft.cl/ligaadef/images/5.png",
                "https://elquisoft.cl/ligaadef/images/6.png",
                "https://elquisoft.cl/ligaadef/images/7.png",
                "https://elquisoft.cl/ligaadef/images/8.png",
                "https://elquisoft.cl/ligaadef/images/9.png",
                "https://elquisoft.cl/ligaadef/images/10.png",
            )

            item {
                LazyRow {
                    item {
                        urlImages.map { valor ->
                            AsyncImage(
                                model = valor,
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        /*****
                        AsyncImage(
                        model = "https://elquisoft.cl/ligaadef/images/1.png",
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                        )

                        AsyncImage(
                        model = "https://elquisoft.cl/ligaadef/images/2.png",
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                        )
                        AsyncImage(
                        model = "https://elquisoft.cl/ligaadef/images/3.png",
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                        )
                        AsyncImage(
                        model = "https://elquisoft.cl/ligaadef/images/4.png",
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                        )
                         *****/

                    }
                }
            }

            item {
                Column() {
                    Spacer(modifier = Modifier.height(10.dp))
                    MyContent()
                }
            }
        }
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
