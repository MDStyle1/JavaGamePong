package com.mds.game.map;

import com.mds.game.gamemode.Game;
import com.mds.game.map.objects.*;
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
    public Map(int sizeX, int sizeY,List<ObjectMap> objectsMap,int ball) {
        this.objectsMap=objectsMap;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        createBall(ball);
    }
    private void createBall(int ball){
        if(ball==1){
            new BallSingleGame(sizeX/2,sizeY/2,10,this,objectsMap);
        }else if(ball==2){
            new Ball(sizeX/2,sizeY/2,10,this,objectsMap);
        }
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
        System.out.println("AddPlayer"+number);
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
