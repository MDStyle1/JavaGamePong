package com.mds.game.util;

public class Coord2d {
    public float x;
    public float y;

    public Coord2d(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Coord2d getDublicate(){
        return new Coord2d(x,y);
    }
    public void print(){
        System.out.println("x = "+x+"  y = "+y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
