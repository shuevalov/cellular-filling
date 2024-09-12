package ru.shuevalov.cellular_filling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.shuevalov.cellular_filling.ui.theme.CellularFillingTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellularFillingTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val similarCells by remember { mutableIntStateOf(0) }
                    val previousCellState by remember { mutableStateOf(CellState.DEFAULT) }
                    var cells by remember { mutableStateOf(listOf<CellState>()) }
                    Text(
                        text = "Cellular filling",
                        fontSize = 24.sp,
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(cells) {
                            CreateCell(cellState = it)
                        }
                    }
                    MakeButton {
                        val newCellState =
                            if (Random.nextBoolean()) CellState.ALIVE else CellState.DEAD
                        cells += newCellState
                    }
                }
            }
        }
    }
}

enum class CellState {
    ALIVE,
    DEAD,
    LIFE,
    DEFAULT
}

@Composable
fun MakeButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
    ) {
        Text(text = "Make")
    }
}

@Composable
fun Cell(
    text: String,
    text2: String,
    lifeState: CellState,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(text) },
        supportingContent = { Text(text2) },
        leadingContent = {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher), // temporarily
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
            )
        }
    )
}

@Composable
fun AliveCell(modifier: Modifier = Modifier) {
    Cell(
        "Alive",
        "and moving!",
        CellState.ALIVE
    )
}

@Composable
fun DeadCell(modifier: Modifier = Modifier) {
    Cell(
        "Dead",
        "or joking",
        CellState.DEAD
    )
}

@Composable
fun LifeCell(modifier: Modifier = Modifier) {
    Cell(
        "Life",
        "coo-coo!",
        CellState.LIFE
    )
}

@Composable
fun CreateCell(
    cellState: CellState,
    modifier: Modifier = Modifier
) {
    when (cellState) {
        CellState.ALIVE -> AliveCell()
        CellState.DEAD -> DeadCell()
        CellState.LIFE -> LifeCell()
        else -> return
    }
}

@Composable
fun CellList(modifier: Modifier = Modifier) {
    LazyColumn {

    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CellularFillingTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val similarCells by remember { mutableIntStateOf(0) }
            val previousCellState by remember { mutableStateOf(CellState.DEFAULT) }
            var cells by remember { mutableStateOf(listOf<CellState>()) }
            Text(
                text = "Cellular filling",
                fontSize = 24.sp,
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(cells) {
                    CreateCell(cellState = it)
                }
            }
            MakeButton {
                val newCellState =
                    if (Random.nextBoolean()) CellState.ALIVE else CellState.DEAD
                cells += newCellState
            }
        }
    }
}