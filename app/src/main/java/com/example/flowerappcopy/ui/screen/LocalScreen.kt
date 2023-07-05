package com.example.flowerappcopy.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.flowerappcopy.ui.screen.component.MapaLocal
import com.example.flowerappcopy.ui.screen.component.SlidingBanner
import com.example.flowerappcopy.ui.screen.component.SlidingBannerLocal

@Composable
fun LocalScreen() {

    val ApiMapKey = "AIzaSyCYjgF-yowRzCjNKKfqIYWHkA2FRHgSbyU"

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            SlidingBanner()
        }
        item {
          //  MapaLocal()
        }
        item {
            SlidingBannerLocal()
        }
    }
}