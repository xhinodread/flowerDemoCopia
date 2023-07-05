package com.example.flowerappcopy.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.flowerappcopy.data.FlowersData
import com.example.flowerappcopy.ui.screen.component.BotonAtras
import com.example.flowerappcopy.ui.screen.component.FlowerCard
import com.example.flowerappcopy.ui.screen.component.ZoomableComposable
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.example.flowerappcopy.ui.theme.ghost_white

@Composable
fun PupularScreen(
    openProducto: (idProd: Int) -> Unit,
    upPress:()-> Unit,
){
    ConstraintLayout {
        val (image, detalleForm) = createRefs()
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .background(colorPrimary)
                .padding(top = 20.dp)
                .constrainAs(image) {
                    top.linkTo(detalleForm.top)
                    bottom.linkTo(detalleForm.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        ) {
         /****   Column(modifier = Modifier
                .height(239.dp)) { *****/
            Row(){
                BotonAtras(ghost_white, upPress)
                Text("Nuestros Productos populares"
                    , color= ghost_white
                    , modifier = Modifier.padding(top=40.dp)
                    , fontSize = 20.sp
                )
            }

        }
        Card(
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            backgroundColor = ghost_white,
            modifier = Modifier
                .fillMaxSize()
                .height(700.dp)
                .padding(top = 50.dp)
                .constrainAs(detalleForm) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(ghost_white)
                    .padding(top = 10.dp)
                ,
            ){
                items(FlowersData.list.size) {
                    FlowerCard(FlowersData.list[it], openProducto )
                }

            }
        }

    }
}