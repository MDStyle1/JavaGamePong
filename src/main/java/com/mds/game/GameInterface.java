package com.mds.game;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.MapInterface;

public interface GameInterface {
    void playPause();
    boolean isPause();
    boolean createMapAndStart();
    void setVisualEventInterface(VisualEventInterface visualEventInterface);
    PlayerControllerInterface getPlayer1();
    PlayerControllerInterface getPlayer2();
    MapInterface getMap();
}
