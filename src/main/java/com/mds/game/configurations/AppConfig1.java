package com.mds.game.configurations;

import com.mds.game.Game;
import com.mds.game.controller.PlayerController;
import com.mds.game.map.Map;
import com.mds.game.map.objects.ObjectMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class AppConfig1 {

    @Bean
    List<ObjectMap> objectMapList(){
        return new ArrayList<ObjectMap>();
    }
    @Bean
    Game game(){
        return new Game();
    }
    @Bean
    Map map(){
        return new Map(100,200,objectMapList());
    }
    @Bean
    PlayerController playerController1(){
        return new PlayerController(map(),game(),1);
    }
    @Bean
    PlayerController playerController2(){
        return new PlayerController(map(),game(),2);
    }

}
