package com.example.flowerappcopy.ui.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.streetview.StreetView

@Composable
fun MapaLocal(){
    val markerPos = LatLng(-29.919313, -71.250570)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markerPos, 12f)
    }

    val listaMarker = listOf(
        LatLng(-29.9119078, -71.2476961),
        LatLng(-29.9119206, -71.2476181),
        LatLng(-29.9120479, -71.246987),
        LatLng(-29.9120283, -71.2468713),
        LatLng(-29.9120351, -71.2468609),
        LatLng(-29.9120678, -71.2467853),
        LatLng(-29.912015, -71.2465232),
        LatLng(-29.9121265, -71.2465881),
        LatLng(-29.9118008, -71.2463054),
        LatLng(-29.9118042, -71.2463076),
        LatLng(-29.9118066, -71.24631),
        LatLng(-29.9118173, -71.2463133),
        LatLng(-29.9118214, -71.2463152),
        LatLng(-29.9118291, -71.2463226),
        LatLng(-29.9118298, -71.2463242),
        LatLng(-29.9118305, -71.2463277),
        LatLng(-29.911829, -71.2463327),
        LatLng(-29.9118294, -71.2463395),
        LatLng(-29.9118275, -71.2463407)
    )

    val circleCenter by remember { mutableStateOf(markerPos) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .height(400.dp)
    ){
        Card(
            shape = RoundedCornerShape(14.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ){
                Text("Donde estamos")
                GoogleMap(
                    modifier= Modifier.fillMaxSize(),
                    properties = MapProperties(mapType = MapType.HYBRID),
                    uiSettings = MapUiSettings(zoomControlsEnabled = true),
                    cameraPositionState = cameraPositionState
                ){
                    Marker(
                        state= MarkerState( position=markerPos),
                        title = "Posicion",
                        snippet = "Estamos ubicados en la tierra",
                    )
                    Circle(
                        center = circleCenter,
                        strokeColor = MaterialTheme.colors.secondaryVariant,
                        radius = 1000.0,
                    )
                    RecorridoMarker(listaMarker)
                }
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun RecorridoMarker(listaMarker: List<LatLng>) {

    if(false){
        listaMarker.map { item ->
            Marker(
                state = MarkerState(position = item),
                title = "Posicion $item",
                snippet = item.toString(),
            )
        }
    }

    val posInicial  =  listaMarker.first()
    val posFinal  =  listaMarker.last()

    Marker(
        state= MarkerState( position=posInicial),
        title = "Posicion $posInicial",
        snippet = posInicial.toString(),
    )
    Marker(
        state= MarkerState( position=posFinal),
        title = "Posicion $posFinal",
        snippet = posFinal.toString(),
    )


    Polyline(
        listaMarker,
        color = Color.Yellow
    )

}