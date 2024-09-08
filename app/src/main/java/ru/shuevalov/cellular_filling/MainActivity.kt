package ru.shuevalov.cellular_filling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.shuevalov.cellular_filling.ui.theme.CellularFillingTheme

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
                    Text(
                        text = "Cellular filling",
                        fontSize = 24.sp,
                    )
                    CellList()
                    MakeButton {

                    }
                }
            }
        }
    }
}

enum class LifeState {
    ALIVE,
    DEAD,
    LIFE
}

@Composable
fun MakeButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text = "Make")
    }
}

@Composable
fun Cell(
    text: String,
    text2: String,
    lifeState: LifeState,
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
fun CellList(modifier: Modifier = Modifier) {
    // TODO
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CellularFillingTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Cellular filling",
                fontSize = 24.sp,
            )
            CellList()
            MakeButton {

            }
        }
    }
}