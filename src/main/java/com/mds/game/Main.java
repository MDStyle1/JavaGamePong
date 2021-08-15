package com.mds.game;

import com.mds.game.client.Request;
import com.mds.game.client.UserInfo;
import com.mds.game.configurations.AppConfig1;
import com.mds.game.gamemode.Game;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main implements MainInterface{
    private Game game;
    private String host = "http://localhost:8080";
    private String name;
    private String password;
    private boolean isAuth = false;
    private boolean playOnline = false;
    private String statusErrorServer;
    private Main() {
    }
    public static MainInterface createMain(){
        return new Main();
    }

    @Override
    public boolean createGame() {
        if(isAuth){
            createGameAndStart();
            return true;
        }
        return false;
    }

    private void createGameAndStart(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig1.class);
        game = (Game) context.getBean("game");
        updateGameEvent();
        game.startingGame();
    }

    @Override
    public String getStatusErrorServer() {
        return statusErrorServer;
    }

    @Override
    public boolean authServer(String name, String password) {
        if(!isAuth){
            Request request = new Request<String>(name,password);
            if(request.getRequest(host +"/login",String.class)){
                playOnline = true;
                isAuth=true;
                this.name=name;
                this.password=password;
                return true;
            }else {
                statusErrorServer=request.status;
                return false;
            }
        }else {
            statusErrorServer="dont loggout";
            return false;
        }
    }

    @Override
    public boolean authServer() {
        if(!isAuth){
            isAuth=true;
            playOnline=false;
            return true;
        }else {
            statusErrorServer = "dont loggout";
            return false;
        }
    }

    @Override
    public boolean registerServer(String name, String password) {
        if(!isAuth){
            Request request = new Request<String>();
            UserInfo userInfo = new UserInfo();
            userInfo.name=name;
            userInfo.password=password;
            if(request.<UserInfo>postRequest(host +"/register",String.class,userInfo)){
                return true;
            }else {
                statusErrorServer=request.status;
                return false;
            }
        }else {
            statusErrorServer = "dont loggout";
            return false;
        }
    }
    private void updateGameEvent(){
        game.setEventGame(new Game.EventGame(){
            @Override
            public void endGame(int score) {
            }
        });
    }
}
