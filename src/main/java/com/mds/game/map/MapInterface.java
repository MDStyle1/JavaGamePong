package com.mds.game.map;

import com.mds.game.map.objects.ObjectMapInterface;

import java.util.List;

public interface MapInterface {
    List<ObjectMapInterface> getObjectsMap();
    int getSizeX();
    int getSizeY();
}
