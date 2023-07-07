package com.example.flowerappcopy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.flowerappcopy.data.Flowers
import com.example.flowerappcopy.data.FlowersCarroCompra
import com.example.flowerappcopy.data.FlowersItemsCarroCompra
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

//@HiltViewModel
class CarroCompraViewModel: ViewModel() {

    private val valor_descto_pago = 5

    private val _cargando = MutableLiveData(false)
    val cargando : LiveData<Boolean> =_cargando

    private val _listaItems = MutableLiveData<List<Flowers>>()
    val listaItems: LiveData<List<Flowers>> = _listaItems

    private val _listaDespacho = MutableLiveData<List<FlowersItemsCarroCompra>>()
    val listaDespacho: LiveData<List<FlowersItemsCarroCompra>> = _listaDespacho

    private val _descto_pago = MutableLiveData(valor_descto_pago) // porcentaje %
    val descto_pago : LiveData<Int> = _descto_pago

    private val _cargo_envio = MutableLiveData(1350)
    val cargo_envio : LiveData<Int> = _cargo_envio

    private val _sub_total_pago = MutableLiveData(0)
    val sub_total_pago : LiveData<Int> = _sub_total_pago

    private val _total_pago = MutableLiveData(0)
    val total_pago : LiveData<Int> = _total_pago


    fun setItem(flower: Flowers){
        ////Log.d("click", flower.toString())
        viewModelScope.launch {
            _cargando.value = true
            FlowersCarroCompra.list.add(flower)
            //  delay(TimeUnit.SECONDS.toMillis(3))
            _listaItems.value = FlowersCarroCompra.list
            procesaDatos()
            _cargando.value = false
            /****
            Log.d("click", listaItems.value?.size.toString() )
            listaItems.value?.map { item ->
                Log.d("click", item.toString())
            }
            *****/
        }
    }

    fun delItem(idFlower: Int){
      //  Log.d("click Wm delItem", idFlower.toString())
      //  Log.d("click Wm delItem", FlowersCarroCompra.list.toString())
        viewModelScope.launch {
            _cargando.value = true
            val listadoL = FlowersCarroCompra.list

            val resto = listadoL.filter { valorF -> valorF.id != idFlower }
            val resp = listadoL.filter { valorF -> valorF.id == idFlower }

            //  Log.d("click Wm delItem listadoL", listadoL.size.toString())
            //  Log.d("click Wm delItem resp 1", resp.size.toString())
            val procResp = resp.drop(1)
            //   Log.d("click Wm delItem resp 2", procResp.size.toString())
            //   Log.d("click Wm delItem resto", resto.size.toString())

            FlowersCarroCompra.list.clear()
            resto.map { valor ->
                FlowersCarroCompra.list.add(valor)
            }
            procResp.map { valor ->
                FlowersCarroCompra.list.add(valor)
            }
            procesaDatos()
            _cargando.value = false
        }
    }

    fun procesaDatos(){
        FlowersCarroCompra.list.sortBy { valor -> valor.name }
        if( _listaItems.value == null )_listaItems.value = FlowersCarroCompra.list
        val grupoCarrito = _listaItems?.value?.groupBy { it.id }
        val listadoCarro = mutableListOf<FlowersItemsCarroCompra>()
      //  Log.d("click pd0", grupoCarrito.toString())
      //  Log.d("click pd0", grupoCarrito?.size.toString())

        if( grupoCarrito?.size!! > 0){

            var eval: Flowers?
            var suma: Int?=0
            var descto_suma: Double?=0.0

            _listaDespacho.value = emptyList()

            grupoCarrito?.forEach { carro ->
                listadoCarro.add(
                    FlowersItemsCarroCompra(
                    carro.value[0].id,
                    carro.value[0].name,
                    carro.value.size
                    )
                )

                /***** Total pago ****/
                eval = FlowersCarroCompra.list.find { valor ->
                    valor.id == carro.value[0].id
                }

                val calculo = (eval?.price!!).toInt().times(carro.value.size)
                suma = suma?.plus(calculo)
                _descto_pago.value = if(suma!! > 35000) valor_descto_pago + 2 else valor_descto_pago
                //descto_suma = suma?.times((_descto_pago.value?.div(100)!!))?.toDouble()
                ///descto_suma = (_descto_pago.value?.div(100)!!)?.toDouble()
                descto_suma = ((_descto_pago.value)?.toDouble()?.let { suma?.times(it)?.div(100) })
                descto_suma = descto_suma?.let { Math.floor(it) }
                Log.d("click descto_suma",  descto_suma.toString())

                /***
                Log.d("click eval ", eval.toString())
                Log.d("click eval precio ", carro.value.size.toString() +" * "+  eval?.price )
                Log.d("click eval precio pagar ", calculo.toString() )
                Log.d("click eval precio suma ", suma.toString() )
                ***/
            }

            /*****
        listadoCarro.map { valor ->
             Log.d("click pd1", valor.toString())
        }
        ******/

            _sub_total_pago.value = suma
            _total_pago.value = suma!! - descto_suma?.toInt()!!
            _listaDespacho.value = listadoCarro
            ////  Log.d("click pd2", listadoCarro.toString())
        }else{
            _descto_pago.value = 0
            _total_pago.value = 0
            _listaDespacho.value = listadoCarro
        }
    }

}