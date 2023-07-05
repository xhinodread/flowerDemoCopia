package com.example.flowerappcopy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.flowerappcopy.R
import com.example.flowerappcopy.data.Flowers
import com.example.flowerappcopy.data.FlowersData
import com.example.flowerappcopy.navigation.Actions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.example.flowerappcopy.ui.theme.*
import com.example.flowerappcopy.ui.screen.component.FlowerCard


@Composable
fun HomeScreen(
    openPopular: () -> Unit,
    openProducto: (idProd: Int) -> Unit,
    upPress:()-> Unit,
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SlidingBanner()
        }
        item {
            CategoryView()
        }
        item {
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Items",
                    style = TextStyle(
                        fontSize = 18.sp,
                    //    fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Ver All",
                    style = MaterialTheme.typography.subtitle2.copy(color = colorPrimary),
                    modifier = Modifier.clickable {
                        openPopular()
                    }
                )
            }
        }
        item {
            PopularFlowersList(openProducto)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SlidingBanner() {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
//            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 20.dp,
    ) { page ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_sale_banner),
            contentScale = ContentScale.FillWidth,
            contentDescription = "sliding_banner_image"
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
private fun CategoryView() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(20.dp)) {
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_chinese_plum_flower
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_flat_flower
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_giftbox
        )
        Spacer(modifier = Modifier.size(10.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            R.drawable.ic_wedding_arch
        )
    }
}

@Composable
fun RoundedCornerIconButton(modifier: Modifier, icon: Int) {
    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(
            onClick = { }, modifier = Modifier
                .align(Alignment.Center)
                .padding(14.dp)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = icon),
                contentDescription = "rounded_corner_icon_button"
            )
        }
    }
}

@Composable
private fun PopularFlowersList(
    openProducto: (idProd: Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(FlowersData.list.size) {
            FlowerCard(FlowersData.list[it], openProducto)
        }
    }
}

@Composable
private fun FlowerCard_(flower: Flowers, openProducto: (idProd: Int) -> Unit ) {
    val context = LocalContext.current

    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {

            Image(
                modifier = Modifier
                    .size(140.dp)
                    .clickable {
                        Toast.makeText(context, "Click producto", Toast.LENGTH_SHORT).show()
                        openProducto(flower.id)
                    }
                ,
                bitmap = ImageBitmap.imageResource(id = flower.image),
                contentDescription = "flower_card"
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = flower.name,
                        style = TextStyle(
                            color = gray,
                            fontSize = 16.sp,
                       ///     fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                        )
                    )
                    Text(
                        text = flower.price,
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                          //  fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        Icons.Default.Add,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "flower_card_icon"
                    )
                }
            }
        }
    }
}