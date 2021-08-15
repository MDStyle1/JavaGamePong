package com.mds.game.controller;

import com.mds.game.gamemode.Game;
import com.mds.game.map.Map;
import com.mds.game.map.objects.Board;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerController implements PlayerControllerInterface{
    public PlayerController(Map map, Game game,int number) {
        this.number = number;
        board = map.addPlayer(number);
    }

    protected Board board;
    @Autowired
    protected Map map;
    @Autowired
    protected Game game;
    protected int number;

    @Override
    public void move(int v) {
        board.eventMove(v);
    }

}
