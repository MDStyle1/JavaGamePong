package com.mds.game;

import com.mds.game.configurations.AppConfig1;
import com.mds.game.gamemode.GameInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main implements MainInterface{
    private GameInterface game;
    private Main() {
    }
    public static MainInterface createMain(){
        return new Main();
    }
    private void createGame(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig1.class);
        game = (GameInterface) context.getBean("game");
    }
    public GameInterface getGame() {
        return game;
    }
}
