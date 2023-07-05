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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.streetview.StreetView

@Composable
fun MapaLocal(){
    val markerPos = LatLng(-29.919313, -71.250570)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markerPos, 12f)
    }

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
                }
                Spacer(Modifier.weight(1f))
            }
        }
    }
}