package ru.shuevalov.cellular_filling.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
//        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        Header(viewModel = viewModel)

        Spacer(modifier = Modifier.size(16.dp))

        LaunchedEffect(key1 = uiState.cells) {
            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount)
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            state = listState
        ) {
            items(uiState.cells) {
                Cell(it)
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                val toastMessageState = viewModel.addNewCells()
                when (toastMessageState) {
                    CellState.LIFE -> {
                        Toast.makeText(context, "Life cell created!", Toast.LENGTH_SHORT).show()
                    }

                    CellState.DEAD -> {
                        Toast.makeText(context, "Life cell removed :(", Toast.LENGTH_SHORT).show()
                    }

                    else -> return@Button
                }
            },
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
    cell: CellState
) {
    Card {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),
            headlineContent = { Text(cell.text, color = cell.color) },
            supportingContent = { Text(cell.text2, color = cell.color) },
            leadingContent = {

                Image(
                    painter = painterResource(id = cell.imageRes), // temporarily
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        )
    }
    Spacer(modifier = Modifier.size(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CellularFillingTheme {
        HomeScreen()
    }
}