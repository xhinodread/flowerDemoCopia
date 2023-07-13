package com.example.flowerappcopy.domain

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import com.example.flowerappcopy.AlertActivity
import com.example.flowerappcopy.R

class AlarmReceiver :BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.e("Alarm", "Alarm recibida at ")


        val intentPend = Intent(context, AlertActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intentPend, PendingIntent.FLAG_IMMUTABLE)


        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        val channelId = "alarm_id"
        context?.let { ctx ->
            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm Demo")
                .setContentText("Notification sent with message $message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            notificationManager.notify(1, builder.build())
        }
        reproducirAudio()
    }

    fun reproducirAudio(){

        val url = "https://elquisoft.cl/despertaluz/uno.mp3"

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
            // on below line we are
            // starting our media player.
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
            }
        } catch (e: Exception) {

            // on below line we are handling our exception.
            e.printStackTrace()
        }
        // on below line we are displaying a toast message as audio player.
        // Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }


}