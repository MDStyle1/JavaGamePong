package com.mds.game;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.MapInterface;

public interface StartCreateInterface {
    void setPlayer(PlayerControllerInterface player);
    void setMap(MapInterface map);

}
