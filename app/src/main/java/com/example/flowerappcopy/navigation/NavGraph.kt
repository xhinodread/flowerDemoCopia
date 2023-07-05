package com.example.flowerappcopy.navigation


import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.flowerappcopy.navigation.Destination.DashBoard
import com.example.flowerappcopy.navigation.Destination.Local
import com.example.flowerappcopy.navigation.Destination.DetalleProducto
import com.example.flowerappcopy.navigation.Destination.HomeLigaFutbol
import com.example.flowerappcopy.navigation.Destination.LoginLiga
import com.example.flowerappcopy.navigation.Destination.Popular

/**
 * Models the screens in the app and any arguments they require.
 */
object Destination {
    const val Splash = "Splash"
    const val Login = "Login"
    const val LoginLiga = "LoginLiga"
    const val DashBoard = "DashBoard"
    const val Local = "Local"
    const val DetalleProducto = "DetalleProducto"
    const val Popular = "Popular"
    const val HomeLigaFutbol = "PerfilUsuario"
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navController: NavHostController) {

    val openDashboard: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val addTask: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val upPress: () -> Unit = {
        navController.popBackStack()
    }

    val openLocal: ()->Unit={
        navController.navigate(Local)
    }

    val openProducto: (idProd: Int)->Unit={valorIdProd ->
        ///Log.d("onClick", "valor: "+ valorIdProd )
        navController.navigate("${DetalleProducto}/${valorIdProd}")
    }

    val openPopular: ()->Unit={
        navController.navigate(Popular)
    }

    val openLigaFutbol: ()->Unit={
        navController.navigate(HomeLigaFutbol)
    }
}