package com.mds.game.gamemode;

import com.mds.game.controller.PlayerController;
import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.Map;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

public class Game implements GameInterface{
    @Autowired
    protected Map map;
    protected EventGame eventGame;
    protected boolean endGame=false;
    protected int delay=5;
    protected boolean pause = true;
    protected float deltaTime=0;
    @Autowired
    private PlayerController playerController1;
    protected boolean canCreateMap =false;
    protected boolean isStartingGame = false;
    private int score;

    protected Thread threadGame;
    protected ThreadGame thGame;

    protected float cfps=0;
    protected int count =0,timer=0;

    public Game() {
    }
    @Override
    public int getScore() {
        return score;
    }
    public void addScore(){
        score++;
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
    @Override
    public PlayerControllerInterface getPlayer2() {
        return null;
    }
    @Override
    public BufferedImage getMap() {
        return map.getImageMap();
    }

    @Override
    public void stopGame() {
        if(!pause)playPause();
        endGame=true;
    }

    protected void playGame() {
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
        if(eventGame!=null) eventGame.endGame(score);
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

    protected class ThreadGame implements Runnable {
        @Override
        public void run() {
            playGame();
        }
    }

}
