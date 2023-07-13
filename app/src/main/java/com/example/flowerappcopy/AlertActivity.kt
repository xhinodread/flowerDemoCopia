package com.example.flowerappcopy

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.flowerappcopy.ui.screen.component.showBasicNotification

class AlertActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAlert()
        }
    }
}

@Composable
fun ComposeAlert(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            onClick = { reproducirAudio() }
        ) {
            Text("Escuchad el audio", color = Color.White)
        }
    }
}

fun reproducirAudio() {

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
}