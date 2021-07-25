package com.mds.game.map.objects;

import com.mds.game.map.Map;

import java.util.List;

public class Ball extends ObjectMap {

    public Ball(int x, int y, int r, Map map) {
        super(x, y, r,map);
        normalVelocity=30;
        currentVelocity=normalVelocity;
        typeObject=TypeObject.ball;
        vectorMove.setVector(1,1);
    }
    @Override
    public void tick(float deltaTime) {
        if(getVelocity()>0){
            if(nextMove(deltaTime)){
                updateNextCoordinate();
                if(checkHitMap()!=true&&checkHitObject()!=true){
                    move();
                }
            }
        }
    }
    @Override
    protected void hit(char a,boolean endGame,int player){
        endGame=false;
        if(endGame){
            map.game.endGame(player);
        }else {
            if(a=='x'){
                vectorMove.reverseX();
            }else if(a=='y') {
                vectorMove.reverseY();
            } else {
                vectorMove.reverse();
            }
        }
        //velocity+=10;
    }
}
