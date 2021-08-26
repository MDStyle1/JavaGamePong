package com.mds.game.gamemode;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.objects.ObjectMapInterface;

import java.util.List;

public interface GameInterface {
    void playPause();
    boolean isPause();
    PlayerControllerInterface getPlayer1();
    List<ObjectMapInterface> getMap();
    int getSizeX();
    int getSizeY();
}
