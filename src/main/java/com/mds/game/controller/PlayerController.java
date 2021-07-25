package com.mds.game.controller;

import com.mds.game.Game;
import com.mds.game.map.Map;
import com.mds.game.map.objects.Board;

public class PlayerController implements PlayerControllerInterface{
    public PlayerController(Map map, Game game,int number) {
        this.number = number;
        this.game = game;
        this.map = map;
        int vy;
        if(number==1){
            vy=20;
        } else vy=map.getSizeY()-20;
        board = new Board(map.getSizeX()/2,vy,20,10,map);
        map.addObjectMap(board);
    }

    protected Board board;
    protected Map map;
    protected Game game;
    protected int number;

    @Override
    public void move(int v) {
        board.eventMove(v);
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
