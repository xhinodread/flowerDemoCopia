package com.example.flowerappcopy.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowerappcopy.ui.theme.ghost_white
import com.example.flowerappcopy.ui.viewmodel.CarroCompraViewModel
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.flowerappcopy.ui.theme.JetPackComposeDemoTheme
import com.example.flowerappcopy.ui.theme.colorPrimary

@Composable
fun CarroDeCompras(
    carroCompraViewModel: CarroCompraViewModel = hiltViewModel()
){
    val cargando by carroCompraViewModel.cargando.observeAsState()
    val listaItems by carroCompraViewModel.listaItems.observeAsState()
    val listaDespacho by carroCompraViewModel.listaDespacho.observeAsState()
    val total_pago by carroCompraViewModel.total_pago.observeAsState()
    val sub_total_pago by carroCompraViewModel.sub_total_pago.observeAsState()
    val descto_pago by carroCompraViewModel.descto_pago.observeAsState()
    val cargo_envio by carroCompraViewModel.cargo_envio.observeAsState()

    carroCompraViewModel.procesaDatos()
    JetPackComposeDemoTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Carrito de compras ",
                style = TextStyle(
                    color = colorPrimary,
                    fontSize = 18.sp,
                    ),
                fontWeight = FontWeight.Bold
            )
           // Text(cargando.toString())
            Text(text = "Detalles del pedido", fontWeight = FontWeight.Bold )


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    //.fillMaxSize()
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(ghost_white)
                    .padding(top = 10.dp),
            ) {
                /*****
                item(){
                Text("Carrito de compras")

                item(){
                Text("Carrito de compras")

                item(){
                Text("Carrito de compras")

                item(){
                Text("Carrito de compras")
                }*****/

                //  val listaCarro = listaItems ////FlowersCarroCompra.list
                //  val listaCarroX = FlowersCarroCompra
                //   val grupoCarrito = listaCarro?.groupBy { it.id }

                /****
                Log.d("click" ,listaCarro.toString())
                Log.d("click" , "\n")
                Log.d("click" , "\n")

                Log.d("click" ,listaCarro.toString())
                Log.d("click" , "\n")
                Log.d("click" , "\n")

                Log.d("click" ,listaCarro.toString())
                Log.d("click" , "\n")
                Log.d("click" , "\n")

                Log.d("click" ,listaCarro.toString())
                Log.d("click" , "\n")
                Log.d("click" , "\n")
                 ****/


                /****
                listaCarroX.listCarro.clear()

                grupoCarrito?.forEach { carro ->
                listaCarroX.listCarro.add(
                FlowersItemsCarroCompra(
                carro.value[0].id,
                carro.value[0].name,
                carro.value.size
                )

                )
                }
                 *****/


                /****
                listaCarroX.listCarro.clear()

                grupoCarrito?.forEach { carro ->
                listaCarroX.listCarro.add(
                FlowersItemsCarroCompra(
                carro.value[0].id,
                carro.value[0].name,
                carro.value.size
                )

                )
                }
                 *****/


                /****
                Log.d("click" , "\n")
                Log.d("click" , "Carrox \n")
                 *****/


                /****
                Log.d("click" , "\n")
                Log.d("click" , "Carrox \n")
                 *****/
                /****
                Log.d("click" , "\n")
                Log.d("click" , "Carrox \n")
                 *****/
                /****
                Log.d("click" , "\n")
                Log.d("click" , "Carrox \n")
                 *****/
                // Log.d("click" , listaCarroX.list.toString())

                /****
                items(listaCarroX.listCarro.size) {
                //  Text(listaCarroX.listCarro[it].name)
                // Text(listaCarroX.listCarro[it].cantidad.toString())
                val seleccion = listaCarro?.filter { valor ->
                valor.id == listaCarroX.listCarro[it].id
                }
                if( seleccion?.size!! > 0 ){
                FlowerCard(seleccion[0], {}, listaCarroX.listCarro[it].cantidad)
                }
                }
                 *****/

                /****
                items(listaCarroX.listCarro.size) {
                //  Text(listaCarroX.listCarro[it].name)
                // Text(listaCarroX.listCarro[it].cantidad.toString())
                val seleccion = listaCarro?.filter { valor ->
                valor.id == listaCarroX.listCarro[it].id
                }
                if( seleccion?.size!! > 0 ){
                FlowerCard(seleccion[0], {}, listaCarroX.listCarro[it].cantidad)
                }
                }
                 *****/


                listaDespacho?.let { valor ->
                    //Log.d("click valor" , valor.toString())
                    items(valor.size) { itemInd ->
                        //Log.d("click" , "itemInd")
                        //Log.d("click" , itemInd.toString() )

                        val seleccion = listaItems?.filter { valorSel ->
                            valorSel.id == valor[itemInd].id
                        }
                       // Log.d("click" , "seleccion")
                       //  Log.d("click" , seleccion.toString())

                        if (seleccion?.size!! > 0) {
                            FlowerCard(seleccion[0], {}, valor[itemInd].cantidad)
                        }
                    }
                }

                /*****
                items(FlowersCarroCompra.list.size) {
                FlowerCard(FlowersCarroCompra.list[it], {} )
                }
                 ****/

                /*****
                items(FlowersCarroCompra.list.size) {
                FlowerCard(FlowersCarroCompra.list[it], {} )
                }
                 ****/

                /*****
                listaDespacho?.let {
                items(it.size) { valll ->
                Text(text = valll.toString() )
                }
                }
                 ****/

                /*****
                listaDespacho?.let {
                items(it.size) { valll ->
                Text(text = valll.toString() )
                }
                }
                 ****/
            }

            if( !listaDespacho.isNullOrEmpty() && listaDespacho?.size!! > 0 ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        //.fillMaxSize()
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(ghost_white)
                        .padding(top = 5.dp),
                ) {
                    item {
                        //total_pago?.let { DetallePedido(it) }
                         DetallePedido(total_pago, sub_total_pago, descto_pago, cargo_envio)
                    }
                }
            }

        }
    }
}

@Composable
fun DetallePedido(total_pago:Int?=0, sub_total_pago:Int?=0, descto_pago:Int?=0, cargo_envio:Int?=0){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Totales del pedido", fontWeight = FontWeight.Bold)
        Row(){
            Text(text = "SubTotal")
            Text(text = " $$sub_total_pago.-")
        }
        Row(){
            Text(text = "Descuento ")
            Text(text = "$descto_pago%  $$total_pago.-")
        }
        Row(){
            Text(text = "Cargo por envio ")
            Text(text = " $$cargo_envio.-")
        }
        Row(){
            Text(text = "Total a pagar ", fontWeight = FontWeight.Bold)
            Text(
                text = " $${total_pago?.plus(cargo_envio!!)}.-",
                style = TextStyle(
                    color = colorPrimary,
                    fontSize = 16.sp,
                ),
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = {
                /// carroCompraViewModel.setItem()
            },
            modifier = Modifier
                .padding(top = 3.dp),
               // .padding(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Enviar pedido de compra")
        }
    }
}