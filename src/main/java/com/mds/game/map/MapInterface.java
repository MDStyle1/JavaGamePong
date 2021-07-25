package com.mds.game.map;

import com.mds.game.map.objects.ObjectMap;

import java.util.List;

public interface MapInterface {
    public List<ObjectMap> getObjectsMap();
    public int getSizeX();
    public int getSizeY();
}
