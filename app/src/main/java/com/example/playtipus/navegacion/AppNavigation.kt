package com.example.playtipus.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playtipus.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable(route = AppScreens.Login.route) {
            PantallaLogin(navController, LoginViewModel())
        }
        composable(route = AppScreens.Register.route) {
            PantallaRegister(navController, RegisterViewModel())
        }
        composable(route = AppScreens.Home.route) {
            PantallaInicio(navController)
        }
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
    }
}
