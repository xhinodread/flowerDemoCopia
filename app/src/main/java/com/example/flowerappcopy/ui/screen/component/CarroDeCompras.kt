package com.example.flowerappcopy.ui.screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flowerappcopy.R
import com.example.flowerappcopy.data.Flowers
import com.example.flowerappcopy.data.FlowersCarroCompra
import com.example.flowerappcopy.data.FlowersData
import com.example.flowerappcopy.data.FlowersItemsCarroCompra
import com.example.flowerappcopy.ui.theme.ghost_white

@Composable
fun CarroDeCompras(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Carrito de compras")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(ghost_white)
                .padding(top = 10.dp),
        ) {
            /*****
            item(){
            Text("Carrito de compras")
            }*****/
            /*****
            item(){
            Text("Carrito de compras")
            }*****/
            val listaCarro = FlowersCarroCompra.list
            val listaCarroX = FlowersCarroCompra
            val grupoCarrito = listaCarro.groupBy { it.id }
            /****
            Log.d("click" ,listaCarro.toString())
            Log.d("click" , "\n")
            Log.d("click" , "\n")
             ****/
            /****
            Log.d("click" ,listaCarro.toString())
            Log.d("click" , "\n")
            Log.d("click" , "\n")
             ****/
            listaCarroX.listCarro.clear()

            grupoCarrito.forEach { carro ->
                listaCarroX.listCarro.add(
                    FlowersItemsCarroCompra(
                        carro.value[0].id,
                        carro.value[0].name,
                        carro.value.size
                    )

                )
            }
            /****
            Log.d("click" , "\n")
            Log.d("click" , "Carrox \n")
             *****/
            /****
            Log.d("click" , "\n")
            Log.d("click" , "Carrox \n")
             *****/
            // Log.d("click" , listaCarroX.list.toString())

            items(listaCarroX.listCarro.size) {
                //  Text(listaCarroX.listCarro[it].name)
                // Text(listaCarroX.listCarro[it].cantidad.toString())
                val seleccion = listaCarro.filter { valor ->
                    valor.id == listaCarroX.listCarro[it].id
                }
                FlowerCard(seleccion[0], {}, listaCarroX.listCarro[it].cantidad)
            }

            /*****
            items(FlowersCarroCompra.list.size) {
            FlowerCard(FlowersCarroCompra.list[it], {} )
            }
             ****/
        }
    }

}