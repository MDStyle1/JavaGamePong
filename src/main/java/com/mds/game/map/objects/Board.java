package com.mds.game.map.objects;

import com.mds.game.map.Map;

public class Board extends ObjectMap{

    public Board(int x, int y, int sizeX, int sizeY, Map map) {
        super(x, y, sizeX, sizeY, map);
        normalVelocity=30;
        currentVelocity =normalVelocity;
        typeObject=TypeObject.board;
        vectorMove.setVector(0,0);
    }
    @Override
    public void tick(float deltaTime) {
        if(vectorMove.getX()!=0||vectorMove.getY()!=0){
            if(nextMove(deltaTime)){
                updateNextCoordinate();
                if(checkHitMap()!=true){
                    move();
                }
            }
        }
    }
    @Override
    protected void hit(char a,boolean endGame,int player){
        //velocity+=10;
    }
    public void eventMove(int v){
        if(v<=-1){
            vectorMove.setVector(-1,0);
        }else if(v>=1){
            vectorMove.setVector(1,0);
        } else vectorMove.setVector(0,0);
    }
}
