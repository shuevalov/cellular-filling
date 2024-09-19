package ru.shuevalov.cellular_filling.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shuevalov.cellular_filling.ui.theme.CellularFillingTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray) // todo: purple color
            .padding(
                top = 44.dp, bottom = 44.dp, start = 16.dp, end = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val uiState by viewModel.uiState.collectAsState()
        val listState = rememberLazyListState()

        Header(viewModel = viewModel)

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            items(uiState.cells) {
                CreateCell(cellState = it)
            }
        }

        LaunchedEffect(key1 = uiState.cells) {
            withContext(Dispatchers.Main) {
                listState.animateScrollToItem(uiState.cells.lastIndex
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = { viewModel.addNewCells() },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Text("Make")
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Cellular filling",
            fontSize = 24.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        OutlinedButton(
            onClick = { viewModel.clearAllCells() },
            modifier = Modifier
        ) {
            Text(text = "Clear")
        }
    }
}

@Composable
fun Cell(
    text: String,
    text2: String,
    color: Color,
) {
    Card {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),
            headlineContent = { Text(text, color = color) },
            supportingContent = { Text(text2, color = color) },
//        leadingContent = {
//            Image(
//                painter = painterResource(id = R.mipmap.ic_launcher), // temporarily
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(CircleShape)
//            )
//        }
//            todo: image
        )
    }
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun CreateCell(cellState: CellState) {
    when (cellState) {
        CellState.ALIVE -> {
            Cell(text = "Alive", text2 = "and moving!", Color.Green)
        }

        CellState.DEAD -> {
            Cell(text = "Dead", text2 = "or pretending to", Color.Red)
        }

        CellState.LIFE -> {
            Cell(text = "Life", text2 = "coo-coo!", Color.Yellow)
        }

        else -> return
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CellularFillingTheme {
        HomeScreen()
    }
}