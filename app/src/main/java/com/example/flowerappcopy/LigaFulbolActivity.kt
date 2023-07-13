package com.example.flowerappcopy

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.flowerappcopy.ui.screen.component.PerfilUsuario

private const val SplashWaitTime: Long = 100


class LigaFulbolActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PerfilUsuario()
            }
        }
    }
}