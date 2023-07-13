package com.example.flowerappcopy.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerappcopy.R
import com.example.flowerappcopy.ui.screen.component.CarroDeCompras
import com.example.flowerappcopy.ui.screen.component.PerfilUsuario
import com.example.flowerappcopy.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    openPopular: () -> Unit,
    openProducto: (idProd: Int) -> Unit,
    upPress:()-> Unit,
) {
    val selectedIndex = remember { mutableStateOf(0) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar()
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize(), color = colorPrimary) {
                    Card(
                        backgroundColor = ghost_white,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Box(modifier = Modifier.padding(bottom = 96.dp)) {
                            when (selectedIndex.value) {
                                0 -> {
                                    HomeScreen(openPopular, openProducto, upPress)
                                }
                                1 -> {
                                    LocalScreen()
                                }
                                2 -> {
                                    CarroDeCompras()
                                }
                                3 -> {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        PerfilUsuario()
                                    }
                                }
                            }
                        } //bodyContent()

                    }
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex)
            },
        )

    }
}

@Composable
fun CustomTopAppBar() {
    TopAppBar(
        elevation = 1.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "FloraGoGo",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                       // fontFamily = FontFamily(Font((R.font.josefin_sans_thin))),
                        fontSize = 22.sp
                    )
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "dashboard_search"
                    )
                }
            }
        },
        backgroundColor = colorPrimary,
    )
}

@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {

    val listItems = listOf("Home", "Location", "Cart", "Profile")

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp)
    ) {
        BottomNavigation(backgroundColor = Color.White) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_home),
                                    isSelected
                                )
                            }
                            1 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_location),
                                    isSelected
                                )
                            }
                            2 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_cart),
                                    isSelected
                                )
                            }
                            3 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.ic_profile),
                                    isSelected
                                )
                            }
                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@Composable
fun TabIcons(icon: ImageBitmap, isTintColor: Boolean) {
    if (isTintColor) {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            colorFilter = ColorFilter.tint(colorPrimary),
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_if"
        )
    } else {
        Image(
            modifier = Modifier.wrapContentSize(),
            bitmap = icon,
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_else"
        )
    }
}
