package com.example.flowerappcopy.ui.screen.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.flowerappcopy.R
import com.example.flowerappcopy.data.Flowers
import com.example.flowerappcopy.data.FlowersCarroCompra
import com.example.flowerappcopy.data.FlowersData
import com.example.flowerappcopy.navigation.Actions
import com.example.flowerappcopy.ui.theme.JetPackComposeDemoTheme
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.example.flowerappcopy.ui.theme.gray
import com.example.flowerappcopy.ui.viewmodel.CarroCompraViewModel

@Composable
fun FlowerCard(
    flower: Flowers, openProducto: (idProd: Int) -> Unit,
    carroCantidad:Int =0,
    carroCompraViewModel: CarroCompraViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)

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
                        Toast
                            .makeText(context, "Click producto", Toast.LENGTH_SHORT)
                            .show()
                        openProducto(flower.id)
                    },
                bitmap = ImageBitmap.imageResource(id = flower.image),
                contentDescription = "flower_card"
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (carroCantidad > 0) "$carroCantidad ${flower.name}" else flower.name,
                        style = TextStyle(
                            color = gray,
                            fontSize = 16.sp,
                            ///     fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                        )
                    )
                    Text(
                        text = "$ ${flower.price}.-",
                        style = TextStyle(
                            color = colorPrimary,
                            fontSize = 16.sp,
                            //  fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                        )
                    )
                    // Text(flower.id.toString())
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.TopStart
                ) {

                    val elModifier = if (carroCantidad > 0) Modifier.width(100.dp) else Modifier

                    Row(
                        modifier = elModifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (carroCantidad > 0) {
                            Icon(
                                Icons.Default.Remove,
                                tint = Color.White,
                                contentDescription = "flower_card_icon",
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(40.dp)
                                    //  .padding(end= 2.dp)
                                    .clickable {
                                        carroCompraViewModel.delItem(flower.id)
                                    }
                            )
                        }
                        Icon(
                            Icons.Default.Add,
                            tint = Color.White,
                            contentDescription = "flower_card_icon",
                            modifier = Modifier
                                .padding(2.dp)
                                .width(40.dp)
                                .clickable {
                                    //FlowersCarroCompra.list.add(flower)
                                    carroCompraViewModel.setItem(flower)
                                }
                        )
                    }
                }
            }
            if (carroCantidad > 0){
                Text("$ ${flower.price.toInt().times(carroCantidad)}.-")
            }
        }
    }

}