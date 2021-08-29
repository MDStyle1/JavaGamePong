package com.mds.game.gamemode;

import com.mds.game.controller.PlayerController;
import com.mds.game.controller.PlayerControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class GameMultiplayerGame extends Game{
    @Autowired
    private PlayerController playerController2;
    @Override
    public PlayerControllerInterface getPlayer2() {
        return playerController2;
    }
}
