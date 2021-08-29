package com.mds.game;

import com.mds.game.client.Request;
import com.mds.game.client.ScoreInfo;
import com.mds.game.client.ScoreList;
import com.mds.game.client.UserInfo;
import com.mds.game.configurations.AppConfigSingleGame;
import com.mds.game.gamemode.Game;
import com.mds.game.gamemode.GameInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
 public class Main implements MainInterface,Game.EventGame{
    private Game game;
    private String host = "http://178.154.241.92:8080";
    private String name;
    private String password;
    private boolean isAuth = false;
    private boolean playOnline = false;
    private String statusErrorServer;
    private EventMain eventMain;
    private Main() {
    }
    public static MainInterface createMain(){
        return new Main();
    }

    @Override
    public void setEventMain(EventMain eventMain) {
        this.eventMain = eventMain;
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigSingleGame.class);
        game = (Game) context.getBean("game");
        game.setEventGame(this);
        game.startingGame();
    }

    @Override
    public String getStatusErrorServer() {
        return statusErrorServer;
    }

    @Override
    synchronized public boolean authServer(String name, String password) {
        if(!isAuth){
            Request request = new Request<String>(name,password);
            if(request.getRequest(host +"/login",String.class)){
                playOnline = true;
                isAuth=true;
                this.name=name;
                this.password=password;
                System.out.println(request.answer);
                return true;
            }else {
                statusErrorServer=request.status;
                System.out.println(statusErrorServer);
                return false;
            }
        }else {
            statusErrorServer="dont loggout";
            return false;
        }
    }

    @Override
    synchronized public boolean authServer() {
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
    synchronized public boolean registerServer(String name, String password) {
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

     @Override
     synchronized public boolean loggout() {
        if(isAuth){
            isAuth=false;
            name=null;
            password=null;
            playOnline=false;
        }
        return true;
     }

    @Override
    public List<ScoreInfo> getScoresTop10() {
        Request request = new Request<ScoreList>();
        if(request.getRequest(host+"/scores/top10",ScoreList.class)){
            ScoreList scoreList= (ScoreList) request.answer;
            return scoreList.list;
        }else {
            statusErrorServer=request.status;
            return null;
        }
    }

    @Override
    public GameInterface getGameInterface() {
        return game;
    }

    @Override
     public void gameStarting() {
        if(eventMain!=null)eventMain.gameIsCreate();
     }

     @Override
     public void endGame(int score) {
        if(playOnline){
            newScore(score);
        }
     }

     private boolean newScore(int score){
        Request request = new Request<String>("Game","MyJavaGame");
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.name = name;
        scoreInfo.score = score;
        request.postRequest(host+"/scores/newscore",String.class,scoreInfo);
        return false;
    }
    public interface EventMain{
        void gameIsCreate();
        void endGame();
    }
}
