package com.mds.game;

import com.mds.game.configurations.AppConfig1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private GameInterface game;
    public Main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig1.class);
        game = (GameInterface) context.getBean("game");
    }

    public GameInterface getGame() {
        return game;
    }
}
