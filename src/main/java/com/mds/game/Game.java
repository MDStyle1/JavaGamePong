package com.mds.game;

import com.mds.game.configurations.AppConfig1;
import com.mds.game.controller.PlayerController;
import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.Map;
import com.mds.game.map.MapInterface;
import com.mds.game.map.objects.ObjectMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Game implements GameInterface{
    @Autowired
    private Map map;
    private boolean endGame=false;
    public int delay=5;
    private boolean pause = true;
    private long lastFrameTime;
    private float deltaTime=0;
    @Autowired
    private PlayerController playerController1;
    @Autowired
    private PlayerController playerController2;
    public VisualEventInterface visualEventInterface;
    private boolean canCreateMap =false;
    private boolean isStartingGame = false;

    private Thread threadGame;
    private ThreadGame thGame;

    private float cfps=0;
    private int count =0,timer=0;

    public Game() {

    }
    @Override
    public void setVisualEventInterface(VisualEventInterface visualEventInterface) {
        this.visualEventInterface = visualEventInterface;
    }

    @Override
    public void playPause() {
        pause = !pause;
    }

    @Override
    public boolean isPause() {
        return pause;
    }

    @Override
    public PlayerControllerInterface getPlayer1() {
        return playerController1;
    }

    @Override
    public PlayerControllerInterface getPlayer2() {
        return playerController2;
    }

    @Override
    public MapInterface getMap() {
        return map;
    }

    private class ThreadGame implements Runnable {
        @Override
        public void run() {
            lastFrameTime = System.nanoTime();
            playGame();
        }
    }
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
                //System.out.println(timer +" Game!"+count+" "+(float)deltaTime);
                cfps-=1;
                count=0;
                timer++;
            }
            try {
                threadGame.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
    public void endGame(int playerWin){
        endGame=true;
        visualEventInterface.eventEndGame(playerWin);
    }
    @Override
    public boolean createMapAndStart(){
        startingGame();
        return true;
    }
    private void startingGame(){
        if(!isStartingGame){
            isStartingGame=true;
            thGame=new ThreadGame();
            threadGame = new Thread(thGame);
            threadGame.start();
            if(visualEventInterface!=null) visualEventInterface.eventGameStart();
        }
    }
}
