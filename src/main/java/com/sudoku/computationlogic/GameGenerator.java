package com.sudoku.computationlogic;

import com.sudoku.problemdomain.Coordinates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static com.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;
public class GameGenerator {
    public static int[][] getNewGameGrid(){
        return unsolveGame(getSolvedGame());
    }
    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());
        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];
        while(!solvable){
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);
            int index = 0;
            while(index<40){
                int xCoordinates = random.nextInt(GRID_BOUNDARY);
                int yCoordinates = random.nextInt(GRID_BOUNDARY);

                if(solvableArray[xCoordinates][yCoordinates] != 0){
                    solvableArray[xCoordinates][yCoordinates] = 0;
                    index++;
                }
            }
            int[][] toBeSovled = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSovled);

            solvable = SudokuSolver.puzzleIsSolvable(toBeSovled);
        }
        return solvableArray;
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        //Value represents potential values for each square. Each value must be allocated 9 times.
        for (int value = 1; value <= GRID_BOUNDARY; value++) {
            //allocations refers to the number of times in which a square has been given a value.
            int allocations = 0;

            //If too many allocation attempts are made which end in an invalid game, we grab the most recent
            //allocations stored in the List below, and reset them all to 0 (empty).
            int interrupt = 0;

            //Keep track of what has been allocated in the current frame of the loop
            List<Coordinates> allocTracker = new ArrayList<>();

            //As a failsafe, if we keep rolling back allocations on the most recent frame, and the game still
            //keeps breaking, after 500 times we reset the board entirely and start again.
            int attempts = 0;

            while (allocations < GRID_BOUNDARY) {

                if (interrupt > 200) {
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    if (attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    //if value results in an invalid game, then re-assign that element to 0 and try again
                    if (GameLogic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    }
                    //otherwise, indicate that a value has been allocated, and add it to the allocation tracker.
                    else {
                        allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }
        }
        System.out.println();
        return newGrid;
    }
    private static void clearArray(int[][] newGrid) {
        for(int xIndex = 0;  xIndex < GRID_BOUNDARY ; xIndex++){
            for(int yIndex = 0 ; yIndex< GRID_BOUNDARY ; yIndex++){
                 newGrid[xIndex][yIndex] = 0;
            }
        }
    }


}
