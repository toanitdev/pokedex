package com.toandev.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.toandev.pokedex.ui.MainViewModel
import com.toandev.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPokemon()

        setContent {
            PokedexTheme {
                HomeScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Box(contentAlignment = Alignment.Center) {
                    Column(Modifier
                        .size(
                            width = 60.dp,
                            height = 60.dp,
                        )) {
                        Surface(color = Color(0xFFDF3C3C), modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()) {

                        }
                        Surface(color = Color.White,modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()) {

                        }
                    }
                    Icon(Icons.Rounded.Search, contentDescription = "")
                }
            }
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .background(Color(0xFFFF7878))
        ) {
            LazyColumn() {
                items(viewModel.pokemons) {
                    PokeItems(it.name, it.sprites!!.frontDefault, it.type?.get(0)!!.name)
                }
            }
        }
    }
}


@Composable
@Preview()
fun PokeItems(name: String = "", sprites: String = "", type: String = "electric") {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(8.dp, 4.dp, 0.dp, 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(50, 0, 0, 50))
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), color = getTypeColor(type)
            ) {}
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), color = Color.White
            ) {}
        }
        Row {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
                    .aspectRatio(1f),
                color = Color.White,
                shape = CircleShape
            ) {
                AsyncImage(
                    model = sprites,
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64452C),
                    fontSize = 18.sp
                )
                Text(text = type.toUpperCase(), fontStyle = FontStyle.Italic)
            }
        }

    }
}

fun getTypeColor(type: String): Color {
    when (type.lowercase()) {
        "normal" -> return Color(0xFFFF9191)
        "poison" -> return Color(0xFFCA6DDA)
        "electric" -> return Color(0xFFFFE770)
        "ground" -> return Color(0xFF997C63)
        "fire" -> return Color(0xFFFF593D)
        "fairy" -> return Color(0xFFFF64C6)
        else -> {
            return Color.White
        }
    }
}



