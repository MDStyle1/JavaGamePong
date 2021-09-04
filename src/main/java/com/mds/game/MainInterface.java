package com.mds.game;

import com.mds.game.chat.Chat;
import com.mds.game.client.ScoreInfo;
import com.mds.game.gamemode.GameInterface;

import java.util.List;

public interface MainInterface {
    boolean authServer(String name,String password);
    boolean authServer();
    boolean registerServer(String name,String password);
    boolean loggout();
    List<ScoreInfo> getScoresTop10();
    String getStatusErrorServer();
    boolean createGame();
    void setEventMain(Main.EventMain eventMain);
    GameInterface getGameInterface();
    boolean isAuth();
    void setEventChat(Chat.EventChat eventChat);
    List<String> getAllMessage();
    void chatSendMessage(String message);
}
