package com.mds.game.map;

import com.mds.game.Game;
import com.mds.game.controller.PlayerController;
import com.mds.game.map.objects.Ball;
import com.mds.game.map.objects.Board;
import com.mds.game.map.objects.ObjectMap;
import com.mds.game.map.objects.ObjectMapInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Map implements MapInterface {
    @Autowired
    protected List<ObjectMap> objectsMap;
    protected int sizeX;
    protected int sizeY;
    protected boolean player1=false;
    protected boolean player2=false;
    protected Board boardPlayer1;
    protected Board boardPlayer2;
    @Autowired
    public Game game;

    @Autowired
    public Map(int sizeX, int sizeY,List<ObjectMap> objectsMap) {
        if(sizeX<50){
            this.sizeX = 50;
        } else this.sizeX = sizeX;
        if(sizeY<200){
            this.sizeY = 200;
        } else this.sizeY = sizeY;
        new Ball(sizeX/2,sizeY/2,10,this,objectsMap);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void addObjectMap(ObjectMap objectMap){
        objectsMap.add(objectMap);
    }

    public List<ObjectMapInterface> getObjectsMap() {
        List<ObjectMapInterface> s = new ArrayList<ObjectMapInterface>(objectsMap);
        return s;
    }

    public List<ObjectMap> getObjectsInMap() { return objectsMap; }

    public void tick(float deltaTime) {
        for (ObjectMap object: objectsMap){
            object.tick(deltaTime);
        }
    }

    public Board addPlayer(int number){
        int vy;
        Board board;
        if(number==1){
            if(!player1){
                vy=10;
                board = new Board(sizeX/2,vy,20,5,this,objectsMap);
            } else board=boardPlayer1;
        } else {
            if(!player2){
                vy=sizeY-10;
                board = new Board(sizeX/2,vy,20,5,this,objectsMap);
            } else board=boardPlayer2;
        }
        return board;
    }
}
