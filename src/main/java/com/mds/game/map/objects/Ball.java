package com.mds.game.map.objects;

import com.mds.game.map.Map;

import java.util.List;

public class Ball extends ObjectMap {

    public Ball(int x, int y, int r, Map map,List<ObjectMap> objectMap) {
        super(x, y, r,map, objectMap);
        normalVelocity=30;
        currentVelocity=normalVelocity;
        typeObject=TypeObject.ball;
        vectorMove.setVector(1,1);
    }
    @Override
    public void tick(float deltaTime) {
        if(currentVelocity>0){
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
        if(endGame){
            map.game.endGame(player);
        }else {
            if(a=='x'){
                vectorMove.reverseX();
            }else if(a=='y') {
                vectorMove.reverseY();
                currentVelocity+=10;
            } else {
                vectorMove.reverse();
            }
        }
    }
}
