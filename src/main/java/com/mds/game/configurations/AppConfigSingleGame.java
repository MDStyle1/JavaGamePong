package com.mds.game.configurations;

import com.mds.game.gamemode.Game;
import com.mds.game.controller.PlayerController;
import com.mds.game.gamemode.GameMultiplayerGame;
import com.mds.game.map.Map;
import com.mds.game.map.MapSingleGame;
import com.mds.game.map.objects.ObjectMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@ComponentScan
public class AppConfigSingleGame {

    @Bean
    List<ObjectMap> objectMapList(){
        return new ArrayList<ObjectMap>();
    }
    @Bean
    Map map(){
        return new MapSingleGame(150,200,objectMapList(),1);
    }
    @Bean
    Game game(){
        return new Game();
    }
    @Bean
    PlayerController playerController1(){
        System.out.println("CreateSinglePlayer1");
        return new PlayerController(map(),game(),1);
    }

}
