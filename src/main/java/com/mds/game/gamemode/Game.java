package com.mds.game.gamemode;

import com.mds.game.controller.PlayerController;
import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.Map;
import com.mds.game.map.objects.ObjectMapInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Game implements GameInterface{
    @Autowired
    private Map map;
    private EventGame eventGame;
    private boolean endGame=false;
    public int delay=5;
    private boolean pause = true;
    private float deltaTime=0;
    @Autowired
    private PlayerController playerController1;
    @Autowired
    private PlayerController playerController2;
    private boolean canCreateMap =false;
    private boolean isStartingGame = false;

    private Thread threadGame;
    private ThreadGame thGame;

    private float cfps=0;
    private int count =0,timer=0;

    public Game() {
    }

    public void setEventGame(EventGame eventGame) {
        this.eventGame = eventGame;
    }

    @Override
    public int getSizeX() {
        return map.getSizeX();
    }

    @Override
    public int getSizeY() {
        return map.getSizeY();
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

    public PlayerControllerInterface getPlayer2() {
        return playerController2;
    }
    @Override
    public List<ObjectMapInterface> getMap() {
        return map.getObjectsMap();
    }

    private void playGame() {
        long currentTime;
        long lastFrameTime = System.nanoTime();
        while (!endGame){
            if(!pause){
                map.tick(deltaTime);
            }
            currentTime = System.nanoTime();
            deltaTime = (currentTime - lastFrameTime)*0.000000001f;
            lastFrameTime = currentTime;
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
        if(eventGame!=null) eventGame.endGame(10);
    }

    public void startingGame(){
        if(!isStartingGame){
            isStartingGame=true;
            thGame=new ThreadGame();
            threadGame = new Thread(thGame);
            threadGame.start();
            if(eventGame!=null) eventGame.gameStarting();
        }
    }

    public interface EventGame{
        void gameStarting();
        void endGame(int score);
    }

    private class ThreadGame implements Runnable {
        @Override
        public void run() {
            playGame();
        }
    }

}
