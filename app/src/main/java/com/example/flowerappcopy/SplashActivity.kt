package com.example.flowerappcopy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SplashWaitTime: Long = 100

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUi()
        }
    }

    @Composable
    fun LoadUi() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Log.d("begin", "loadUi Screen")

            /****/
            LaunchedEffect(key1 = true) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(SplashWaitTime)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }
            }

            /*****/
        }
    }
}