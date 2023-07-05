package com.example.flowerappcopy.ui.screen.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.flowerappcopy.R
import com.example.flowerappcopy.navigation.Actions
import com.example.flowerappcopy.ui.screen.RoundedCornerIconButton
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.example.flowerappcopy.ui.theme.ghost_white

@Composable
fun BotonAtras(
    colorTexto: Color = colorPrimary,
    upPress:()-> Unit = {}
){
    var colorFondo: Color=ghost_white
    var paddinTop : Dp =10.dp
    if(colorTexto != colorPrimary){
        colorFondo = colorPrimary
        paddinTop = 30.dp
    }

  /****  Button(
        onClick = {},
        modifier = Modifier
            .padding(top = paddinTop, end = 20.dp)
            .width(40.dp)
            .height(40.dp)
        ,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorFondo
        ),
    ) { ***/
    /****
        Text(
            modifier = Modifier.size(50.dp).wrapContentHeight()
            ,
            text = "<",
            color = colorTexto,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            overflow = TextOverflow.Visible,
            fontWeight = FontWeight.Bold
        )
    ****/
  ///  }

   /*****  RoundedCornerIconButton(
        modifier = Modifier,
       icon = painterResource(id = R.drawable.chevron_back_foreground)
    ) *****/

    Column(
        modifier = Modifier
            .padding(top=paddinTop, start=10.dp, end = 20.dp)
            .width(50.dp)

    ) {
        Icon(
            Icons.Default.ArrowBackIos ,
            contentDescription = "Atras",
            tint=colorTexto,
            modifier = Modifier
                .background(colorFondo)
                .clickable {
                    upPress()
            }
        )
    }

}