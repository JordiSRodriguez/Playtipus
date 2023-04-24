package com.example.playtipus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

public val brush = Brush.linearGradient(
    colors = listOf(
        Color(0xFF7ADFEC),
        Color(0xFF6A61C0)
    )
)
val brush1 = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF6A61C0),
        Color(0xFF7ADFEC)
    )
)

@Composable
fun PantallaInicio(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)

    ) {

        LazyColumn {
            item {
                Spacer(
                    modifier = Modifier
                        .padding(40.dp)
                )
                Tittulo()
                Objetos(R.drawable.lightyear, "Buzz Lightyear")
                Objetos(R.drawable.lego, "Lego")
                Objetos(R.drawable.minion, "Bob Minions Peluche")
                Objetos(R.drawable.pollo, "Pollo de Goma")
                Objetos(R.drawable.steve, "Steve Minecraft Figura")
                Objetos(R.drawable.piggy, "Piggy Peluche")
                Objetos(R.drawable.sirena, "Peluche Cabeza de Sirena")
                Spacer(
                    modifier = Modifier
                        .padding(30.dp)
                )
            }

        }
        Row(
            modifier = Modifier
                .background(brush1)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Botón atrás",
                modifier = Modifier.clickable { navController.popBackStack() }
                    .padding(top = 23.dp, start = 8.dp)
                    .size(40.dp))
            Buscador(Modifier
                .fillMaxHeight(), vertical = Alignment.Top)
            MenuDesplegable()
        }

        Botones()

    }
}

@Composable
fun MenuDesplegable() {
    IconButton(onClick = {}) {
        Icon(
            painterResource(id = R.drawable.imagenmenu),
            "menu desplegable",
            modifier = Modifier
                .size(60.dp)
                .padding(top = 29.dp, start = 3.dp, end = 2.dp)
        )
    }

}

@Composable
fun Tittulo() {
    Text(
        "Recomendado para ti",
        fontSize = 35.sp,
        fontWeight = FontWeight(weight = 700),
        modifier = Modifier
            .padding(start = 30.dp)


    )
}

@Composable
fun Objetos(
    id: Int,
    texto: String,
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Imagenes(id)
        Column(modifier = Modifier
            .padding(start = 10.dp)) {
            TextoTitulo(texto)
            ImagenEstrellas()
            TextoPrecio()
        }
    }

}

@Composable
fun Imagenes(
    id: Int,
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(100.dp)
            .background(color = Color(0xFFFFFFFF))
    )
}

@Composable
fun Buscador(modifier: Modifier, vertical: Alignment.Vertical) {
    var baraBus by remember { mutableStateOf("") }
    TextField(
        value = baraBus, onValueChange = { newText -> baraBus = newText },
        placeholder = { Text(text = "Buscar en Playtipus") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFFFFFFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), modifier = Modifier
            .padding(top = 15.dp),
        shape = RoundedCornerShape(30.dp)

    )
}

@Composable
fun Botones() {

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
    ) {
        Box(
            modifier = Modifier

                .background(brush)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(
                    onClick = {},
                )
                {

                    Image(
                        painterResource(id = R.drawable.imagencasa),
                        "ir al inicio",
                        modifier = Modifier
                            .size(46.dp)
                    )


                }
                IconButton(
                    onClick = {},

                    ) {
                    Image(
                        painterResource(id = R.drawable.imagenmapa1),
                        "ir al inicio",
                        modifier = Modifier
                            .size(46.dp)


                    )
                }
                IconButton(
                    onClick = {},

                    ) {
                    Image(
                        painterResource(id = R.drawable.imagencorazon),
                        "ir al inicio",
                        modifier = Modifier
                            .size(48.dp)


                    )
                }
                IconButton(
                    onClick = {},

                    ) {
                    Image(
                        painterResource(id = R.drawable.imagencarrito),
                        "ir al inicio",
                        modifier = Modifier
                            .size(55.dp)


                    )
                }
                IconButton(
                    onClick = {},

                    ) {
                    Image(
                        painterResource(id = R.drawable.imagenengranaje),
                        "ir al inicio",
                        modifier = Modifier
                            .size(46.dp)


                    )
                }
            }
        }
    }
}

@Composable
fun ImagenEstrellas() {
    Row() {
        Image(
            painterResource(id = R.drawable.estrelladorada),
            "calificacion",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)
        )
        Image(
            painterResource(id = R.drawable.estrelladorada),
            "calificacion",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)


        )
        Image(
            painterResource(id = R.drawable.estrelladorada),
            "calificacion",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)


        )
        Image(
            painterResource(id = R.drawable.estrelladorada),
            "calificacion",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)
            //.offset(5.dp,-35.dp)

        )
        Image(
            painterResource(id = R.drawable.estrelladorada),
            "calificacion",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)
            //.offset(5.dp,-35.dp)

        )
    }
}

@Composable
fun TextoTitulo(
    texto : String,
) {

    Text(
        texto,
        fontSize = 20.sp,
        maxLines = 1,
        fontWeight = FontWeight(weight = 500),
        modifier = Modifier

    )

}

@Composable
fun TextoPrecio() {
    Text(
        "00.00",
        fontSize = 35.sp,
        fontWeight = FontWeight(weight = 650),
        modifier = Modifier

    )
}