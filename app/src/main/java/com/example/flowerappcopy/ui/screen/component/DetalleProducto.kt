package com.example.flowerappcopy.ui.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.flowerappcopy.data.FlowersData
import com.example.flowerappcopy.ui.theme.JetPackComposeDemoTheme
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.example.flowerappcopy.ui.theme.ghost_white
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.example.flowerappcopy.R
import com.example.flowerappcopy.ui.theme.Purple40


@Composable
fun DetalleProducto(
    idProd: Int,
    upPress:()-> Unit,
) {
    val context = LocalContext.current
    JetPackComposeDemoTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ghost_white),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, detalleForm) = createRefs()
                    val laFlower = FlowersData.list[idProd]
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(340.dp)
                            .constrainAs(image) {
                                top.linkTo(detalleForm.top)
                                bottom.linkTo(detalleForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        //Text("Foto y boton atras id : $idProd ${laFlower.name}")
                        /*****
                        Image(
                            modifier = Modifier.size(220.dp),
                            bitmap = ImageBitmap.imageResource(id = laFlower.image),
                            contentDescription = "flower_card",
                            contentScale = ContentScale.FillBounds
                        )
                        *******/
                        Column() {
                            BotonAtras(upPress = upPress)
                            ZoomableComposable(laFlower.image)
                        }
                    }
                    Card(
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        backgroundColor = colorPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(700.dp)
                            .padding(top = 170.dp)
                            .constrainAs(detalleForm) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 1.dp, bottom = 2.dp),
                                text = "Bouquet de flores "+laFlower.name,
                                textAlign = TextAlign.Left,
                                fontSize = 23.sp,
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 1.dp, bottom = 2.dp),
                                text = "Incluye: "+laFlower.sub_name,
                                textAlign = TextAlign.Left,
                                fontSize = 13.sp,
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 30.dp),
                            ){
                                Text(
                                    modifier = Modifier
                                        .width(100.dp),
                                       // .padding(top = 10.dp, bottom = 30.dp),
                                    text = laFlower.price,
                                    textAlign = TextAlign.Left,
                                    fontSize = 25.sp,
                                )
                                Text("Cantidad: 0", fontSize = 25.sp,)

                            }

                            Canvas(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Allows you to draw a line between two points (p1 & p2) on the canvas.
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, 0f),
                                    strokeWidth = 2.0F
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, bottom = 10.dp),
                                text = "Descripción: ",
                                textAlign = TextAlign.Left,
                                fontSize = 20.sp,
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 30.dp),
                                text = laFlower.descr,
                                textAlign = TextAlign.Left,
                                fontSize = 15.sp,
                            )
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .padding(top = 30.dp, bottom = 34.dp)
                                    .align(Alignment.CenterHorizontally)
                                   // .background(ghost_white)
                                ,
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = ghost_white
                                )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .fillMaxWidth(),
                                    text = "Agregar al Carro ->",
                                    color = colorPrimary,
                                    style = MaterialTheme.typography.button,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ZoomableComposable(laFlower_image:Int) {
    // Reacting to state changes is the core behavior of Compose.
    // We use the state composable that is used for holding a
    // state value in this composable for representing the current
    // value scale(for zooming in the image)
    // & translation(for panning across the image).
    // Any composable that reads the value of counter will
    // be recomposed any time the value changes.
    var scale by remember { mutableStateOf(0.5f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    // In the example below, we make the Column composable zoomable
    // by leveraging the Modifier.pointerInput modifier
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()
                        do {
                            val event = awaitPointerEvent()
                            scale *= event.calculateZoom()
                            val offset = event.calculatePan()
                            offsetX += offset.x
                            offsetY += offset.y
                        } while (event.changes.any { it.pressed })
                    }
                }
            }
    ) {
        // painterResource method loads an image resource asynchronously
        val imagepainter = painterResource(id = R.drawable.flower_logo)
        // We use the graphicsLayer modifier to modify the scale & translation
        // of the image.
        // This is read from the state properties that we created above.
        Image(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                    //translationX = 0F,
                    //translationY = 0F
                ),
           // painter = imagepainter,
            bitmap = ImageBitmap.imageResource(id = laFlower_image),
            contentDescription = "androids launcher default launcher background image"
        )
    }
}

