package ru.shuevalov.cellular_filling.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    var uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var currentCell: CellState = CellState.DEFAULT
    private var sameCellsCount: Int = 0

    private fun getRandomCell() = if (Random.nextBoolean()) CellState.ALIVE else CellState.DEAD

    private fun addCell(cell: CellState) {
        currentCell = cell
        _uiState.update { currentState ->
            currentState.copy(cells = uiState.value.cells + cell)
        }
    }

    private fun removeLifeCell(): Boolean { //
        val noLifeList = mutableListOf<CellState>()
        var lifeIsRemoved = false
        uiState.value.cells.reversed().forEach {
            if (it == CellState.LIFE) {
                if (lifeIsRemoved) {
                    noLifeList.add(it)
                } else {
                    lifeIsRemoved = true
                }
            } else {
                noLifeList.add(it)
            }
        }
        if (lifeIsRemoved) {
            _uiState.update { currentState ->
                currentState.copy(cells = noLifeList.reversed().toList())
            }
            return true
        } else {
            return false
        }
    }

    fun addNewCells(): CellState { // return cell state for toast message
        val newCell = getRandomCell()
        val same = currentCell == newCell // for check without previous current cell
        addCell(newCell)
        if (same) {
            sameCellsCount++
            if (sameCellsCount == 2) {
                when (newCell) {
                    CellState.ALIVE -> {
                        addCell(CellState.LIFE)
                        sameCellsCount = 0
                        return CellState.LIFE // make toast "Life cell created"
                    }

                    CellState.DEAD -> {
                        val lifeIsRemoved = removeLifeCell()
                        sameCellsCount = 0
                        return if (lifeIsRemoved) {
                            CellState.DEAD // make toast "Life cell removed"
                        } else {
                            CellState.DEFAULT // life isn't removed, no toast
                        }
                    }

                    else -> return CellState.DEFAULT
                }
            }
        } else {
            sameCellsCount = 0
        }
        return CellState.DEFAULT
    }

    fun clearAllCells() {
        _uiState.update { currentState ->
            currentState.copy(cells = emptyList())
        }
        sameCellsCount = 0
        currentCell = CellState.DEFAULT
    }
}