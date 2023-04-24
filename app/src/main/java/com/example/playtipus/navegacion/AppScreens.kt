package com.example.playtipus.navegacion

sealed class AppScreens(val route: String) {
    object Login: AppScreens("login")
    object Register: AppScreens("register")
    object Home: AppScreens("home")
    object SplashScreen: AppScreens("splash")
}
