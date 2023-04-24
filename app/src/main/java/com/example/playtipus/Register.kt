package com.example.playtipus

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
    fun PantallaRegister(navController: NavController, viewModel: RegisterViewModel) {
        Scaffold() {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush)
            ) {
                Register(navController, viewModel)
            }
        }
    }

    @Composable
    fun Register(navController: NavController, viewModel: RegisterViewModel) {
        val email: String by viewModel.email.observeAsState("")
        val fullName: String by viewModel.fullName.observeAsState("")
        val userName: String by viewModel.userName.observeAsState("")
        val password: String by viewModel.password.observeAsState("")
        val confirmPassword: String by viewModel.confirmPassword.observeAsState("")
        val registerEnable: Boolean by viewModel.registerEnable.observeAsState(false)

        var persona1 = Persona("","","","")
        persona1.email = email
        persona1.nombreCompleto = fullName
        persona1.nombreUsuario = userName
        persona1.password = password

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Bienvenido()
                Spacer(modifier = Modifier.padding(20.dp))
                FieldEmail(email) { viewModel.onEmailChanged(it) }
                CampoFullName(fullName) { viewModel.onFullNameChanged(it) }
                CampoUserName(userName) { viewModel.onUserNameChanged(it) }
                FieldPassword(password) { viewModel.onPasswordChanged(it) }
                CampoConfirmPassword(confirmPassword) { viewModel.onConfirmPasswordChanged(it) }
                Spacer(modifier = Modifier.padding(10.dp))
                BotonRegister(registerEnable, email, fullName, userName, password, confirmPassword, navController)
                Spacer(modifier = Modifier.padding(5.dp))
                RegisterApps()
                EntrarCuenta(navController)
            }
        }
    }

    @Composable
    fun Bienvenido() {
        Column() {

            Row() {

                Text(
                    text = "¡Hola!",
                    color = Color(0xFF575757),
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 70.dp, start = 40.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.playtipus
                    ),
                    contentDescription = "Logo Playtipus",
                    modifier = Modifier
                        .height(150.dp)
                        .size(170.dp)
                        .padding(top = 70.dp, start = 5.dp)
                )
            }
            Text(
                text = "Bienvenido",
                color = Color(0xFF575757),
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 40.dp)
            )
        }
    }

    @Composable
    fun FieldEmail(email: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = email, onValueChange = { onTextFieldChanged (it) },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF000000),
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.DarkGray
            )
        )
    }
    @Composable
    fun CampoFullName(fullName: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = fullName, onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Nombre completo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF000000),
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.DarkGray
            )
        )
    }

    @Composable
    fun CampoUserName(userName: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = userName, onValueChange = { onTextFieldChanged(it) },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Nombre de usuario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF000000),
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.DarkGray
            )
        )
    }

    @Composable
    fun FieldPassword(password: String, onTextFieldChanged: (String) -> Unit) {
        TextField(
            value = password, onValueChange = { onTextFieldChanged(it) },
            placeholder = { Text(text = "Contraseña") },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF000000),
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.DarkGray
            )
        )
    }

    @Composable
    fun CampoConfirmPassword(confirmPassword: String, onTextFieldChanged: (String) -> Unit) {
            TextField(
                value = confirmPassword, onValueChange = {onTextFieldChanged(it)},
                placeholder = { Text(text = "Confirmar Contraseña") },
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF000000),
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.DarkGray
                )
            )
    }

    @Composable
    fun BotonRegister(
        registerEnable: Boolean,
        email: String,
        fullName: String,
        userName: String,
        password: String,
        confirmPassword: String,
        navController: NavController
    ) {
        val context = LocalContext.current
        Button(
            onClick = { if (!registerEnable) {
                if (email.isBlank() || fullName.isBlank() || userName.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                    Toast.makeText(
                        context, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT
                    ).show()
                }} else if (password != confirmPassword) {
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                        .show()
                }
             else {
                navController.navigate(route = "home")
                Toast.makeText(context, userName + " registrado correctamente", Toast.LENGTH_SHORT).show()
            } },
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF4B4B4B),
                contentColor = Color.White
            )
        ) {
            Text(text = "Registrarse",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun RegisterApps() {
        Column {
            Text(
                text = "Registrate con:",
                fontSize = 17.sp,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(horizontal = 40.dp),
            )
            Row(modifier = Modifier.padding(horizontal = 40.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Logo Google",
                    modifier = Modifier
                        .padding(7.dp)
                        .size(50.dp)
                        .clickable { }
                )
                Image(painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Logo Facebook",
                    modifier = Modifier
                        .padding(7.dp)
                        .size(50.dp)
                        .clickable { }
                )
                Image(painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "Logo Twitter",
                    modifier = Modifier
                        .padding(7.dp)
                        .size(50.dp)
                        .clickable { }
                )
            }
        }
    }

    @Composable
    fun EntrarCuenta(navController: NavController) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "¿Tienes una cuenta?",
                modifier = Modifier
                    .padding(start = 40.dp, top = 7.dp)
                    .clickable { },
                fontSize = 16.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Entra",
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp, top = 7.dp)
                    .clickable { navController.popBackStack() },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF464646),
                textAlign = TextAlign.End
            )
        }
    }

