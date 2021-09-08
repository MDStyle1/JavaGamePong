package com.mds.game;

import com.mds.game.chat.Chat;
import com.mds.game.client.Request;
import com.mds.game.client.ScoreInfo;
import com.mds.game.client.ScoreList;
import com.mds.game.client.UserInfo;
import com.mds.game.configurations.AppConfigSingleGame;
import com.mds.game.gamemode.Game;
import com.mds.game.gamemode.GameInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
 public class Main implements MainInterface,Game.EventGame{
    private Request request;
    private Game game;
    private String host =
//            "http://localhost:8080";
//            "http://178.154.241.92:8080";
            "http://188.243.224.61:8080";
    private String name;
    private String password;
    private boolean isAuth = false;
    private boolean playOnline = false;
    private String statusErrorServer;
    private EventMain eventMain;
    private Chat chat;
    private Chat.EventChat eventChat =null;
    private Main() {
    }
    public static MainInterface createMain(){
        return new Main();
    }

    public Request getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    @Override
    public boolean isAuth() {
        return isAuth;
    }

    @Override
    public void setEventMain(EventMain eventMain) {
        this.eventMain = eventMain;
    }
    @Override
    public void setEventChat(Chat.EventChat eventChat) {
        this.eventChat = eventChat;
        if(chat!=null) chat.setEventChat(eventChat);
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
            request = new Request();
            Map map = new HashMap<String,String>();
            map.put("name",name);
            map.put("password",password);
            if(request.<String,Map>postRequest(host +"/login",String.class,map)){
                playOnline = true;
                isAuth=true;
                this.name=name;
                this.password=password;
                chat = new Chat(this);
                if(eventChat!=null) chat.setEventChat(eventChat);
                new Thread(chat).start();
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
            Request request = new Request();
            UserInfo userInfo = new UserInfo();
            userInfo.name=name;
            userInfo.password=password;
            if(request.<String,UserInfo>postRequest(host +"/register",String.class,userInfo)){
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
            if(chat!=null) chat.stopChat();
            chat=null;
            request=null;
        }
        return true;
     }

    @Override
    public List<ScoreInfo> getScoresTop10() {
        if(isAuth){
            if(request.<ScoreList>getRequest(host+"/scores/top10",ScoreList.class)){
                ScoreList scoreList= (ScoreList) request.answer;
                return scoreList.list;
            }else {
                statusErrorServer=request.status;
                return null;
            }
        }
        return null;
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
     @Override
     public List<String> getAllMessage(){
         if(chat!=null) return chat.getList();
         return null;
     }
     @Override
     public void chatSendMessage(String message){
        if(chat!=null)chat.sendMessage(message);
     }

    private boolean newScore(int score){
        Request request = new Request("Game","MyJavaGame");
        Map map = new HashMap<String,String>();
        map.put("name",name);
        map.put("password",password);
        request.<String,Map>postRequest(host +"/login",String.class,map);
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.name = name;
        scoreInfo.score = score;
        request.<String,ScoreInfo>postRequest(host+"/scores/newscore",String.class,scoreInfo);
        return false;
    }
    public interface EventMain{
        void gameIsCreate();
        void endGame();
    }
}
