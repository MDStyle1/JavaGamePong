package com.mds.game;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.objects.ObjectMapInterface;

import java.util.List;

public interface GameInterface {
    void playPause();
    boolean isPause();
    boolean createMapAndStart();
    void setVisualEventInterface(VisualEventInterface visualEventInterface);
    PlayerControllerInterface getPlayer1();
    PlayerControllerInterface getPlayer2();
    List<ObjectMapInterface> getMap();
    boolean authServer(String name,String password);
    boolean authServer();
    boolean registerServer(String name,String password);
    String getStatusErrorServer();
}
