package com.mds.game;

import com.mds.game.controller.PlayerController;
import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.Map;
import com.mds.game.map.MapInterface;
import com.mds.game.map.objects.Ball;
import com.mds.game.map.objects.Board;

public class Game implements GameInterface{
    private Map map;
    private boolean endGame=false;
    public int delay=5;
    private boolean pause = true;
    private long lastFrameTime;
    private float deltaTime=0;
    private PlayerController playerController;
    public VisualEventInterface visualEventInterface;
    private boolean canPreStart=false;

    private Thread threadGame;
    private ThreadGame thGame;

    private Game(VisualEventInterface visualEventInterface) {
        this.visualEventInterface= visualEventInterface;
        map = new Map(100,200);
        map.addObjectMap(new Ball(50,100,10,map));
        map.game = this;
        playerController = new PlayerController(map,this,1);
        playerController.setMap(map);
        visualEventInterface.setGameInterface(this);
        canPreStart=true;
        visualEventInterface.createGame();
    }
    public static GameInterface createGame(VisualEventInterface visualEventInterface) {
        GameInterface gameInterface = new Game(visualEventInterface);
        return gameInterface;
    }
    private boolean isStartingGame = false;
    private void startingGame(){
        if(isStartingGame){

        } else {
            isStartingGame=true;
            thGame=new ThreadGame();
            threadGame = new Thread(thGame);
            threadGame.start();
            visualEventInterface.gameStart();
        }
    }
    @Override
    public void playPause() {
        pause = !pause;
    }

    public boolean isPause() {
        return pause;
    }

    private class ThreadGame implements Runnable {
        @Override
        public void run() {
            lastFrameTime = System.nanoTime();
            playGame();
        }
    }
    private float cfps=0;
    private int count =0,i=0;
    private void playGame() {
        while (endGame==false){
            if(pause==false&&endGame==false){
                map.tick(deltaTime);
                //pause=true;
            }
            cfps=cfps+deltaTime;
            long currentTime = System.nanoTime();
            deltaTime = (currentTime - lastFrameTime)*0.000000001f;
            lastFrameTime = currentTime;
            if(cfps>1){
                //System.out.println(i +" Game!"+count+" "+(float)deltaTime);
                cfps-=1;
                count=0;
                i++;
            }
            try {
                threadGame.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        playGame();
    }
    public void endGame(int playerWin){
        endGame=true;
        System.out.println("Wiiiin");
        visualEventInterface.endGame(playerWin);
    }
    @Override
    public boolean startCreateGame(StartCreateInterface start){
        if(canPreStart)
        {
            start.setMap(map);
            start.setPlayer(playerController);
            startingGame();
            return true;
        } else return false;

    }
}
