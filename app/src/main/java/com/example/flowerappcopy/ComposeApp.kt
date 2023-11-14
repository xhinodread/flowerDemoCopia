package com.example.flowerappcopy


import android.os.Build
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flowerappcopy.navigation.Actions
import com.example.flowerappcopy.navigation.Destination
import com.example.flowerappcopy.ui.screen.DashboardScreen
import com.example.flowerappcopy.ui.screen.ElquiLigaFutbol
import com.example.flowerappcopy.ui.screen.LocalScreen
import com.example.flowerappcopy.ui.screen.LoginLigaFutbolScreen
import com.example.flowerappcopy.ui.screen.LoginScreen
import com.example.flowerappcopy.ui.screen.PupularScreen
import com.example.flowerappcopy.ui.screen.component.DetalleProducto
import com.example.flowerappcopy.ui.screen.component.PerfilUsuario

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    Log.d("begin", "ComposeApp Screen")

    val actions = remember(navController) { Actions(navController) }

    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Local) {

            composable(Destination.Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(Destination.LoginLiga) {
                LoginLigaFutbolScreen(actions.openLigaFutbol)
            }

            composable(Destination.DashBoard) {
                DashboardScreen(actions.openPopular, actions.openProducto, actions.upPress)
                ///DashboardScreen(actions.openProducto.apply { it })
            }

            composable(
                route = Destination.DetalleProducto+"/{idProd}",
                arguments = listOf(navArgument("idProd") { type = NavType.IntType })
            ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("idProd")
                    requireNotNull(id)
                    DetalleProducto(id, actions.upPress)
            }

            composable(Destination.Popular) {
                PupularScreen(actions.openProducto, actions.upPress)
            }

            composable(Destination.Perfil) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    PerfilUsuario()
                }
            }

            composable(Destination.HomeLigaFutbol) {
                //PerfilUsuario()
                ElquiLigaFutbol()
            }

            composable(Destination.Local) {
                LocalScreen()
            }


        }
    }
}