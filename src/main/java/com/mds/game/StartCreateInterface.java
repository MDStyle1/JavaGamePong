package com.mds.game;

import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.MapInterface;

public interface StartCreateInterface {
    public void setPlayer(PlayerControllerInterface player);
    public void setMap(MapInterface map);

}
