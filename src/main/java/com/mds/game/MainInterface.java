package com.mds.game;

import com.mds.game.gamemode.GameInterface;
import com.mds.game.map.objects.ObjectMapInterface;

import java.util.List;

public interface MainInterface {
    boolean authServer(String name,String password);
    boolean authServer();
    boolean registerServer(String name,String password);
    boolean loggout();
    String getStatusErrorServer();
    boolean createGame();
    void setEventMain(Main.EventMain eventMain);
    GameInterface getGameInterface();
}
