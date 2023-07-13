package com.example.flowerappcopy.ui.screen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.flowerappcopy.data.AlarmItem
import com.example.flowerappcopy.model.AlarmScheduler
import com.example.flowerappcopy.model.AlarmSchedulerImpl
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmCompUno(){
    val context = LocalContext.current
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(context)
    var alarmItem: AlarmItem? = null

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        var secondText by remember { mutableStateOf("") }
        var messageText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = secondText, onValueChange = {
                secondText = it
            },
                label = {
                    Text(text = "Delay Second")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = messageText, onValueChange = {
                messageText = it
            },
                label = {
                    Text(text = "Message")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    alarmItem = AlarmItem(
                        alarmTime = LocalDateTime.now().plusSeconds(
                            secondText.toLong()
                        ),
                        message = messageText
                    )
                    alarmItem?.let(alarmScheduler::schedule)
                    secondText = ""
                    messageText = ""
                }) {
                    Text(text = "Schedule")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    alarmItem?.let(alarmScheduler::cancel)
                }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

}