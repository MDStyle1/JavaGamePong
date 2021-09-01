package com.mds.game.gamemode;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.objects.ObjectMapInterface;

import java.awt.image.BufferedImage;
import java.util.List;

public interface GameInterface {
    void playPause();
    boolean isPause();
    PlayerControllerInterface getPlayer1();
    PlayerControllerInterface getPlayer2();
    int getSizeX();
    int getSizeY();
    void stopGame();
    int getScore();
    BufferedImage getMap();
    String getPosBall();
    void setEventMapGraphics(Game.EventMapGraphics eventMapGraphics);
}
