@file:Suppress("DEPRECATION")

package com.example.flowerappcopy.ui.screen.component

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.provider.Settings.Global.getString
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import coil.compose.AsyncImage
import com.example.flowerappcopy.AlertActivity
import com.example.flowerappcopy.R
import com.example.flowerappcopy.ui.theme.ghost_white
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun  PerfilUsuario() {

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Perfil de Usuario")
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ4D3xt5q3rk7jjEA1l9wx7nooKnF-hrgy8Vwmg9gRqDIdacN8Os3dhhCvaaKxsOccMHw&usqp=CAU",
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
        /***/
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            value = "Nombre del usuario",
            onValueChange = {},
            label = {Text("Nombre")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Yellow),
            readOnly = true
        )
        /****/
        OutlinedTextField(
            value = "Direccion",
            onValueChange = {},
            label = {Text("Direccion")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Yellow),
            readOnly = true
        )

        if(false){
            Row(){
                Button(
                    onClick = { showBasicNotification(context) }
                ) {
                    Text("Notificacion", color = Color.White)
                }
                Button(
                    onClick = { reproducirAudio() }
                ) {
                    Text("Reproducir audio", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            AlarmCompUno()
        }




    }
}

fun showBasicNotification(context:Context) {

    val intent = Intent(context, AlertActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(context, "water_notification")
        .setSmallIcon(R.drawable.flower_logo)
        .setContentTitle("Drink some water!")
        .setContentText("It passed one hour since you drank some water...")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    val notificationManager = context.getSystemService(NotificationManager::class.java)
    notificationManager.notify(Random.nextInt(), notification)
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyContent(){
    var context = LocalContext.current
    // Declare a string that contains a url
    val mUrl = "https://elquisoft.cl/ligaadef/liga_tabla.asp"

    // Adding a WebView inside AndroidView
    // with layout as full screen

    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                   // ViewGroup.MarginLayoutParams.MATCH_PARENT ,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                //setInitialScale(99)
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                loadUrl(mUrl)
            }
        },
        update = {
            it.loadUrl(mUrl)
        },
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Red)
           // .width(IntrinsicSize.Max)
    )
}


fun reproducirAudio(){

    //val url = "https://elquisoft.cl/despertaluz/uno.mp3"
   // val url = "https://elquisoft.cl/despertaluz/dos.mp3"
    val url = "https://elquisoft.cl/despertaluz/tres.mp3"
    //val url = "https://elquisoft.cl/despertaluz/audio_test.mp3"
    // val url = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"

    /*****
    val mediaPlayer_: MediaPlayer? = MediaPlayer().apply {
        setAudioStreamType(AudioManager.STREAM_MUSIC)
        setDataSource(url)
        prepareAsync() // might take long! (for buffering, etc)
        start()
    }
    ******/
    val mediaPlayer = MediaPlayer()
    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
    mediaPlayer.setAudioAttributes(
        AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()
    )

    // on below line we are running a try
    // and catch block for our media player.
    try {
        // on below line we are setting audio
        // source as audio url on below line.
        mediaPlayer.setDataSource(url)
        // on below line we are
        // preparing our media player.
        mediaPlayer.prepareAsync()
        //mediaPlayer.prepare()

        // on below line we are
        // starting our media player.
       // mediaPlayer.start()
        /****/
        mediaPlayer.setOnPreparedListener { mp ->
            mp.start()
        }
        /*****/

    } catch (e: Exception) {

        // on below line we are handling our exception.
        e.printStackTrace()
    }
    // on below line we are displaying a toast message as audio player.
   // Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()



}
