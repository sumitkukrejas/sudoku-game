package com.sudoku.problemdomain;

import com.sudoku.computationlogic.SudokuUtilities;
import com.sudoku.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    private final GameState gameState;
    public final int[][] gridState;

    public SudokuGame(GameState gameState,int[][] gridState) {
        this.gridState = gridState;
        this.gameState = gameState;
    }

    public static final int GRID_BOUNDARY = 9;

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
