package com.example.playtipus

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.playtipus.navegacion.AppScreens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaLogin(navController: NavController, viewModel: LoginViewModel) {
    Scaffold() {
        Box(
            Modifier
                .fillMaxSize()
                .background(brush)
        ) {
            Login(navController, viewModel)
        }
    }
}

@Composable
fun Login(navController: NavController, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(false)

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            LogoPlaytipus()
            BienvenidoMsg()
            txt1()
            Spacer(modifier = Modifier.padding(4.dp))
            CampoEmail(email) { viewModel.onLoginChanged(it, password) }
            CampoPassword(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Recuerdame()
                ForgotPassword()
            }
            Spacer(modifier = Modifier.padding(7.dp))
            BotonLogin(loginEnable, email, password, navController)
            Biometria()
            Row {
            LoginApps()
            Contactanos()
            }
            CreateCuenta(navController)
        }
    }
}

@Composable
fun BotonLogin(loginEnable: Boolean, email: String, password: String, navController: NavController) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (!loginEnable) {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(
                        context, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "Email o contraseña incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                navController.navigate(route = AppScreens.Home.route)
                Toast.makeText(context, "Login correcto", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = 40.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4B4B4B),
            contentColor = Color.White,
        ),
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun LogoPlaytipus() {
    Image(
        painter = painterResource(
            id = R.drawable.playtipus
        ),
        contentDescription = "Logo Playtipus",
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .fillMaxWidth()
            .size(175.dp)
    )
}

@Composable
fun BienvenidoMsg() {
    Text(
        text = "Bienvenid@",
        fontSize = 55.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF575757),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun txt1() {
    Text(
        text = "Millones de juguetes te están esperando",
        fontSize = 15.sp,
        color = Color(0xFF575757),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}


@Composable
fun ForgotPassword() {
    Text(
        text = "Olvidaste tu contraseña?",
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .clickable { }
            .fillMaxWidth(),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF575757),
        textAlign = TextAlign.End
    )
}

@Composable
fun Recuerdame() {
    val checked = remember { mutableStateOf(true) }
    CheckboxNombre(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        label = "Recuerdame",
        colors = CheckboxDefaults.colors(
            checkmarkColor = Color(0xFF4B4B4B),
            disabledColor = Color(0xFF969696),
            uncheckedColor = Color(0xFF969696),
            checkedColor = Color(0xFF00BFFF),
        )
    )
}

@Composable
fun CheckboxNombre(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        modifier = modifier
            .height(30.dp)
            .padding(start = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Text(
            label,
            color = Color(0xFF575757),
        )
    }
}

@Composable
fun CampoPassword(password: String, onTextFieldChanged: (String) -> Unit) {
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
fun Biometria() {
    Column() {
        Text(
            text = "Acceder con biometría",
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            fontSize = 17.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center
        )
        Image(painter = painterResource(
            id = R.drawable.huella
        ),
            contentDescription = "Huella dactilar",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { }
        )
    }
}

@Composable
fun LoginApps() {
    Column {
        Text(
            text = "Iniciar sesión con:",
            fontSize = 17.sp,
            color = Color(0xFF000000),
            modifier = Modifier
                .padding(start = 40.dp),
        )
        Row(modifier = Modifier.padding(start = 27.dp)) {
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
fun Contactanos() {
    val context = LocalContext.current
    Column(Modifier.padding(end = 40.dp, top = 1.dp)) {
        Text(
            text = "Contactanos:",
            fontSize = 17.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Telefono",
            modifier = Modifier
                .padding(top = 7.dp)
                .clickable {
                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:666666666")))
                }
                .size(50.dp)
                .align(Alignment.CenterHorizontally),
            tint = Color(0xFF424242)
        )
    }
}

@Composable
fun CreateCuenta(navController: NavController) {
    Row() {
        Text(
            text = "¿Aun no tienes cuenta?",
            modifier = Modifier
                .padding(start = 40.dp, top = 10.dp),
            fontSize = 16.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Start
        )
        Text(
            text = "Regístrate",
            modifier = Modifier
                .padding(start = 40.dp, top = 9.dp)
                .clickable { navController.navigate(route = AppScreens.Register.route) },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF464646),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun CampoEmail(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
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