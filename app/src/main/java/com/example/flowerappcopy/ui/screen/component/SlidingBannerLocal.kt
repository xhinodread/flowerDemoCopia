package com.example.flowerappcopy.ui.screen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerappcopy.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlidingBannerLocal(){
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            //     AnalyticsService.sendPageSelectedEvent(page)
            Log.d("onClick", "page: $page")
        }
    }

    HorizontalPager(
        count = 5,
        state = pagerState,
        itemSpacing = 20.dp,
    ) { page ->
        Log.d("onClick", "HorizontalPager page: $page")

        Box() {
            Card(
                shape = RoundedCornerShape(14.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .width(500.dp)
                        .padding(20.dp),
                    bitmap = ImageBitmap.imageResource(id = BannersLocal.losBanners[page]),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "sliding_banner_image"
                )
                Text(
                    text = BannersLocal.losTextosDelBanner[page],
                    style = MaterialTheme.typography.subtitle2.copy(color = Color.Red),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .padding(start = 30.dp)
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Estamos en Pedro Pablo con la Ruta 5",
                    style = MaterialTheme.typography.subtitle2.copy(color = Color.Yellow),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top=150.dp, start = 25.dp)
                )
            }
        }
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

object BannersLocal{
    val losBanners = listOf(
        R.drawable.frontisejemploubica,
        R.drawable.frontisejemplolocal2,
        R.drawable.frontisejemlocal3,
        R.drawable.frontisejemlocal4,
        R.drawable.frontisejemplolocal5
    )

    val losTextosDelBanner = listOf(
        "Vista general",
        "Entrada principal",
        "Vista lateral",
        "Vista costado",
        "Vista alejada",
    )
}
