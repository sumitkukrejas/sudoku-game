package com.sudoku.persistance;

import com.sudoku.problemdomain.IStorage;
import com.sudoku.problemdomain.SudokuGame;

import java.io.*;

public class LogicStorageImpl implements IStorage {
    private static File GAME_DATA = new File(System.getProperty("user.home"),
                                                "gamedata.txt"
                                        );

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        }
        catch (IOException e){
            throw new IOException("Unable to access game data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try{
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("File Not Found");
        }
    }
}
