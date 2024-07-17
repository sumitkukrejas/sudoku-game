package com.sudoku.buildlogic;

import com.sudoku.computationlogic.GameLogic;
import com.sudoku.persistance.LogicStorageImpl;
import com.sudoku.problemdomain.IStorage;
import com.sudoku.problemdomain.SudokuGame;
import com.sudoku.userinterface.ControlLogic;
import com.sudoku.userinterface.IUserInterfaceContract;
import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LogicStorageImpl();
        try{
            initialState = storage.getGameData();
        }
        catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }
        IUserInterfaceContract.EventListener  uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}