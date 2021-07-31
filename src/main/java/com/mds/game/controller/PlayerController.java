package com.mds.game.controller;

import com.mds.game.Game;
import com.mds.game.map.Map;
import com.mds.game.map.objects.Board;

public class PlayerController implements PlayerControllerInterface{
    public PlayerController(Map map, Game game,int number) {
        this.number = number;
        this.game = game;
        this.map = map;
        board = map.addPlayer(number);
    }

    protected Board board;
    protected Map map;
    protected Game game;
    protected int number;

    @Override
    public void move(int v) {
        board.eventMove(v);
    }

}
