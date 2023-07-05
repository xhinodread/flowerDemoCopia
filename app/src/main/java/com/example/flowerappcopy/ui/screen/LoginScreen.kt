package com.example.flowerappcopy.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.flowerappcopy.R
import com.example.flowerappcopy.ui.theme.JetPackComposeDemoTheme
import com.example.flowerappcopy.ui.theme.*

@Composable
fun LoginScreen(openDashboard: () -> Unit) {
    Log.d("begin", "Login Screen")

    val context = LocalContext.current
    JetPackComposeDemoTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(ghost_white),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ConstraintLayout {
                    val (image, loginForm) = createRefs()
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(280.dp)
                            .constrainAs(image) {
                                top.linkTo(loginForm.top)
                                bottom.linkTo(loginForm.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        HeaderView()
                    }

                    Card(
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                        backgroundColor = ghost_white,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp)
                            .constrainAs(loginForm) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)
                        ) {

                            val loginText = "Ingreso a su cuenta"
                            val loginWord = "Ingreso"
                            val loginAnnotatedString = buildAnnotatedString {
                                append(loginText)
                                addStyle(
                                    style = SpanStyle(
                                        color = dark_gray,
                                     //   fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                                    ),
                                    start = 0,
                                    end = loginText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = colorPrimary,
                                     //   fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                                    ),
                                    start = 0,
                                    end = loginWord.length
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 20.dp),
                                text = loginAnnotatedString,
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp,
                            )

                            Text(
                                text = "Email Cliente",
                                style = MaterialTheme.typography.subtitle1.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                            )
                            CustomStyleTextField(
                                "Email Cliente",
                                R.drawable.ic_email,
                                KeyboardType.Email,
                                VisualTransformation.None
                            )

                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.subtitle1.copy(color = gray),
                                modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                            )
                            CustomStyleTextField(
                                "Password",
                                R.drawable.ic_password,
                                KeyboardType.Password,
                                PasswordVisualTransformation()
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ){
                                Text(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .padding(top = 20.dp)
                                        .clickable {
                                                   Toast.makeText(context, "Olvidé mi clave", Toast.LENGTH_SHORT).show()
                                        },
                                    text = "Olvidó su clave",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.subtitle2.copy(color = colorPrimary)
                                )
                            }

                            Button(
                                onClick = openDashboard,
                                modifier = Modifier
                                    .padding(top = 30.dp, bottom = 34.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                    text = "Login",
                                    color = Color.White,
                                    style = MaterialTheme.typography.button
                                )
                            }

                            val signInText = "No tiene una cuenta? Registrese"
                            val signInWord = "Registrese"
                            val signInAnnotatedString = buildAnnotatedString {
                                append(signInText)
                                addStyle(
                                    style = SpanStyle(
                                        color = light_gray,
                                      //  fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                                    ),
                                    start = 0,
                                    end = signInText.length
                                )
                                addStyle(
                                    style = SpanStyle(
                                        color = colorPrimary,
                                      //  fontFamily = FontFamily(Font(R.font.josefin_sans_thin))
                                    ),
                                    start = signInText.indexOf(signInWord),
                                    end = signInText.length
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        Toast.makeText(context, "Registrarse", Toast.LENGTH_SHORT).show()
                                    },
                                text = signInAnnotatedString,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomStyleTextField(
    placeHolder: String,
    leadingIconId: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        value = textState.value,
        onValueChange = { valueChanged ->
            textState.value = valueChanged
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .size(18.dp),
                        bitmap = ImageBitmap.imageResource(id = leadingIconId),  // material icon
                        colorFilter = ColorFilter.tint(colorPrimary),
                        contentDescription = "custom_text_field"
                    )
                    Canvas(
                        modifier = Modifier.height(24.dp)
                    ) {
                        // Allows you to draw a line between two points (p1 & p2) on the canvas.
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0F
                        )
                    }
                }
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorPrimary,
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.White,
            trailingIconColor = Color.White,
//            disabledTextColor = NaviBlue
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )

    /*OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        value = textState.value,
        onValueChange = { valueChanged ->
            textState.value = valueChanged
        },
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .size(18.dp),
                        bitmap = ImageBitmap.imageResource(id = leadingIconId),  // material icon
                        colorFilter = ColorFilter.tint(colorPrimary),
                        contentDescription = "custom_text_field"
                    )
                    Canvas(
                        modifier = Modifier.height(24.dp)
                    ) {
                        // Allows you to draw a line between two points (p1 & p2) on the canvas.
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0F
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        activeColor = colorPrimary,
        inactiveColor = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )*/
    /* TextField(
         value = textState.value,
         onValueChange = { valueChanged ->
             textState.value = valueChanged
         },
         modifier = Modifier.fillMaxSize(),
         placeholder = Text(text = placeHolder, style = TextStyle(color = text_hint_color)),
         leadingIcon = Row(
             modifier = Modifier.fillMaxSize(),
             verticalAlignment = Alignment.CenterVertically,
             content = {
                 Image(
                     modifier = Modifier.padding(start = 10.dp, end = 10.dp).size(18.dp),
                     bitmap = imageResource(id = leadingIconId),  // material icon
                     colorFilter = ColorFilter.tint(colorPrimary),
                 )
                 Canvas(
                     modifier = Modifier.preferredHeight(24.dp)
                 ) {
                     // Allows you to draw a line between two points (p1 & p2) on the canvas.
                     drawLine(
                         color = Color.LightGray,
                         start = Offset(0f, 0f),
                         end = Offset(0f, size.height),
                         strokeWidth = 2.0F
                     )
                 }
             }
         ),
         activeColor = colorPrimary,
         inactiveColor = Color.Transparent,
         shape = RoundedCornerShape(10.dp),
         backgroundColor = Color.White,
         textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
         keyboardType = keyboardType,
         visualTransformation = visualTransformation
     )*/
}


@Composable
fun HeaderView() {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = ImageBitmap.imageResource(id = R.drawable.loginsplashuno),
        contentScale = ContentScale.FillWidth,
        contentDescription = "header_view_login_bg"
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Image(
            modifier = Modifier.wrapContentWidth(),
            bitmap = ImageBitmap.imageResource(id = R.drawable.flower_logo),
            contentDescription = "header_view_flower_logo"
        )
        Text(
            text = "Flora'sGoGo",
            color = Color.White,
            style = TextStyle(
                fontSize = 40.sp,
              //  fontFamily = FontFamily(Font(R.font.josefin_sans_thin)),
                letterSpacing = 2.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(openDashboard = {})
}