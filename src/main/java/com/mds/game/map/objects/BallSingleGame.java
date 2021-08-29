package com.mds.game.map.objects;

import com.mds.game.map.Map;

import java.util.List;

public class BallSingleGame extends Ball{
    public BallSingleGame(int x, int y, int r, Map map, List<ObjectMap> objectMap) {
        super(x, y, r, map, objectMap);
    }

    @Override
    protected void hit(char a, boolean endGame, int player) {
        if(endGame){
            if(player==1){
                vectorMove.reverseY();
                map.game.addScore();
            } else map.game.endGame(player);
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
