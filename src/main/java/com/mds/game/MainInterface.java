package com.mds.game;

public interface MainInterface {
    boolean authServer(String name,String password);
    boolean authServer();
    boolean registerServer(String name,String password);
    String getStatusErrorServer();
    boolean createGame();

}
