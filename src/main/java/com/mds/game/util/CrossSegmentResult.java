package com.mds.game.util;

public class CrossSegmentResult {
    private int x;
    private int y;
    private boolean cross = false;

    public CrossSegmentResult(int x, int y, boolean cross) {
        this.cross = cross;
        if(cross){
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCross() {
        return cross;
    }
}
