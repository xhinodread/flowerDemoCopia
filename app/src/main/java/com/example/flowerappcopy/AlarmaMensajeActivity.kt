package com.example.flowerappcopy

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowerappcopy.data.AlarmItem
import com.example.flowerappcopy.model.AlarmScheduler
import com.example.flowerappcopy.model.AlarmSchedulerImpl
import com.example.flowerappcopy.ui.theme.JetPackComposeDemoTheme
import com.example.flowerappcopy.ui.theme.colorPrimary
import com.example.flowerappcopy.ui.viewmodel.CarroCompraViewModel
import com.example.flowerappcopy.ui.viewmodel.SliderViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class AlarmaMensajeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // JetPackComposeDemoTheme {
                ComposeAlarmaMensaje()
          //  }
        }
    }
}

@Composable
fun ComposeAlarmaMensaje(
    sliderViewModel: SliderViewModel = hiltViewModel()
){
    val sliderValue by sliderViewModel.sliderValue.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){
          Column(
              modifier = Modifier
                  .padding(5.dp)
                  //.padding(start= 10.dp)
                  .fillMaxWidth()
                ///  .widthIn(100.dp, 300.dp)
          ) {
              ConstraintLayout {
                  val (image, detalleForm) = createRefs()
                  Box(
                      contentAlignment = Alignment.Center,
                      modifier = Modifier
                          .height(150.dp)
                          .constrainAs(image) {
                              top.linkTo(detalleForm.top)
                              bottom.linkTo(detalleForm.top)
                              start.linkTo(parent.start)
                              end.linkTo(parent.end)
                          }) {
                      Text("Alarma mensaje",  modifier = Modifier.padding(top =50.dp), fontSize = 30.sp)
                  }
                  Card(
                      shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                      backgroundColor = colorPrimary,
                      modifier = Modifier
                          .fillMaxSize()
                          .height(700.dp)
                          .padding(top = 100.dp)
                          .constrainAs(detalleForm) {
                              bottom.linkTo(parent.bottom)
                              start.linkTo(parent.start)
                              end.linkTo(parent.end)
                          },
                  ) {
                     Config(sliderValue!!)
                  }
              }
          }
          /****
          Column(
              modifier = Modifier
                  .padding(10.dp)
          ) {
              Text("Tiempo")
              displayTxtClock()
          }
          *****/
        }
    }
}

@Composable
fun Config(sliderValue: Float){
    Card(
        elevation = 10.dp,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(35.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                MyUISlider(sliderValue)
                Spacer(modifier = Modifier.height(45.dp))
                AlarmCompAgenda(sliderValue)
                TimePick()
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmCompAgenda(sliderValue: Float) {
    val context = LocalContext.current
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(context)
    var alarmItem: AlarmItem? = null

    Button(
        onClick = {
            val tiempoSpera: Long = Math.round(sliderValue).toLong()
            Toast.makeText(context, "Tiempo: $tiempoSpera", Toast.LENGTH_SHORT).show()
            alarmItem = AlarmItem(
                alarmTime = LocalDateTime.now().plusSeconds(tiempoSpera),
                message = "MENSAJE AGENDADO RECIBIDO"
            )
            alarmItem?.let(alarmScheduler::schedule)
    }) {
        Text(text = "Activar alarma")
    }
}
@Composable
private fun MyUISlider(
    sliderValue: Float,
    sliderViewModel: SliderViewModel = hiltViewModel()
) {
    Text(text = "Tiempo de la alarma en Segundos", fontSize = 20.sp)
    Slider(
        modifier = Modifier
            .width(width = 200.dp),
        value = sliderValue,
        onValueChange = { sliderValue_ ->
            //sliderValue = sliderValue_
            sliderViewModel.cambioValor(sliderValue_)
        },
        onValueChangeFinished = {
            // this is called when the user completed selecting the value
            Log.d("AlarmaMensajeActivity", "sliderValue = $sliderValue")
        },
        valueRange = 1f..10f,
        steps = 10
    )

    Text(text = Math.round(sliderValue).toString(), fontSize = 40.sp)

}

// Creating a composable function
// to create a Time Picker
// Calling this function as content
// in the above function
@Composable
fun TimePick(){

    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    Column(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // On button click, TimePicker is
        // displayed, user can select a time
        Button(
            onClick = { mTimePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
        ) {
            Text(text = "Hora", color = Color.White)
        }
        // Add a spacer of 100dp
        Spacer(modifier = Modifier.size(10.dp))
        // Display selected time
        Text(text = "Selected Hora: ${mTime.value}", fontSize = 20.sp)
    }
}

// on below line we are creating a
// function to get current date and time.
@Composable
fun displayTxtClock() {
    // on below line we are
    // creating a column on below sides.
    Column(
        // on below line we are adding padding
        // padding for our column and filling the max size.
        Modifier,
           // .fillMaxSize()
          //  .fillMaxHeight()
           // .fillMaxWidth(),
        // on below line we are adding
        // horizontal alignment for our column
        horizontalAlignment = Alignment.CenterHorizontally,
        // on below line we are adding
        // vertical arrangement for our column
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            // on below line we are specifying text to display.
            text = "Hoy",

            // on below line we are specifying
            // modifier to fill max width.
            modifier = Modifier.fillMaxWidth(),

            // on below line we are
            // specifying text alignment.
            textAlign = TextAlign.Center,

            // on below line we are
            // specifying color for our text.
            color = Color.Black,

            // on below line we are specifying font weight
            fontWeight = FontWeight.Bold,

            // on below line we are updating font size.
            fontSize = 25.sp,
        )

        // on below line we are creating a spacer.
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating and initializing
        // variable for simple date format.
        val sdf = SimpleDateFormat("dd-MM-yyyy '\n\nHora\n'HH:mm:ss z")

        // on below line we are creating a variable for
        // current date and time and calling a simple
        // date format in it.
        val currentDateAndTime = sdf.format(Date())

        Text(
            // on below line we are
            // specifying text to display.
            text = currentDateAndTime,

            // on below line we are specifying
            // modifier to fill max width.
            modifier = Modifier.fillMaxWidth(),

            // on below line we are
            // specifying text alignment.
            textAlign = TextAlign.Center,

            // on below line we are specifying
            // color for our text.
            color = Color.Black,

            // on below line we are specifying font weight
            fontWeight = FontWeight.Bold,

            // on below line we are updating font size.
            fontSize = 25.sp,
        )

    }
}