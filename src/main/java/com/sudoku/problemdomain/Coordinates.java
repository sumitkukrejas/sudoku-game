package com.sudoku.problemdomain;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    public final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() !=o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        return x== coordinates.x && y==coordinates.y;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }
}
