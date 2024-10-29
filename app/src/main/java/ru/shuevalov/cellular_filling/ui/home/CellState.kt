package ru.shuevalov.cellular_filling.ui.home

import androidx.compose.ui.graphics.Color
import ru.shuevalov.cellular_filling.R

enum class CellState(
    var text: String,
    var text2: String,
    var color: Color,
    var imageRes: Int
) {
    ALIVE(
        text = "Alive",
        text2 = "and moving!",
        color = Color.Green,
        imageRes = R.mipmap.heart
    ),
    DEAD(
        text = "Dead",
        text2 = "or pretending to",
        color = Color.Red,
        imageRes = R.mipmap.skull
    ),
    LIFE(
        text = "Life",
        text2 = "coo-coo!",
        color = Color.Yellow,
        imageRes = R.mipmap.bird
    ),
    DEFAULT("", "", Color.White, 0)
}