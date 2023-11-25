@file:OptIn(ExperimentalMaterial3Api::class)

package by.eapp.gamesearcher.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.eapp.gamesearcher.R


@Composable
fun HeightSpacer(
    height: Dp = 10.dp
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}

@Composable
fun WidthSpacer(
    width: Dp = 10.dp
) {
    Spacer(
        modifier = Modifier
            .width(width)
    )
}
@Composable
fun MyText(
    text: String,
    fontSize: Int,
    weight: Int = 400,
    color: Long = 0xFF666666
) {
    Text(
        text = text,
        style = TextStyle(
            color = Color(color),
            fontWeight = FontWeight(weight),
            fontSize = fontSize.sp
        )
    )
}

@Composable
fun HomeScreen(
    //navController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF171717)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            HeightSpacer(20.dp)
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                SearchBar()
                WidthSpacer(15.dp)
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            HeightSpacer(15.dp)
            GenreRow()
            HeightSpacer(15.dp)
            GamesColumn()
        }
    }
}

@Composable
fun SearchBar() {
    var searchingText by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = searchingText,
        shape = RoundedCornerShape(15.dp),
        onValueChange = { text ->
            searchingText = text.trimStart { it == '0' }
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            MyText(
                fontSize = 14,
                text = stringResource(id = R.string.search)
            )
        },
        modifier = Modifier
            .padding(0.5.dp)
            .fillMaxWidth(0.9f)
            .height(50.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            )
    )


}


@Composable
fun GenreRow(){
    val itemsList = listOf("RPG", "Action", "Arcade", "Shooter", "Adventures")
    var selectedChip by rememberSaveable {
    mutableStateOf(itemsList[0])
    }
    val selectedColor: Color = Color(0xFFE77625)
    val unselectedColor: Color = Color(0xFF282D5E)
    val contextForToast = LocalContext.current.applicationContext
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(itemsList) { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedChip),
                onClick = {
                    selectedChip = item
                    Toast.makeText(contextForToast, selectedChip, Toast.LENGTH_SHORT).show()
                },
                label = {
                    Text(text = item, color = Color.White)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = selectedColor,
                    containerColor = unselectedColor,
                )
            )
        }
    }
}

@Composable
fun GamesColumn() {
    //test
    val painter = painterResource(id = R.drawable.thewitcher)
    val title = "Test: The witcher 3"
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        content = {
            items(20) {index ->
            GameCard(painter = painter, contentDescription = null , title = title)
            }
        })

}

@Composable
fun GameCard(
    painter: Painter,
    contentDescription: String?,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),

    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {

            }
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                MyText(
                    text = title,
                    fontSize = 11,
                    weight = 600
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun previewSearchBar() {
    HomeScreen()
}