package com.example.flowerappcopy.ui.screen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlidingBanner() {
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                bitmap = ImageBitmap.imageResource(id = Banners.losBanners[page]),
                contentScale = ContentScale.FillWidth,
                contentDescription = "sliding_banner_image"
            )
            Text(
                text = "$page",
                style = MaterialTheme.typography.subtitle2.copy(color = Color.Red),
                fontSize=20.sp,
                modifier = Modifier
                    .padding(20.dp)
            )

        }
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

object Banners{
    val losBanners = listOf(
        R.drawable.ic_sale_banner2,
        R.drawable.ic_sale_banner,
        R.drawable.ic_sale_banner3,
        R.drawable.ic_sale_banner5,
        R.drawable.ic_sale_banner4
    )
}

