package com.mds.game.map;

import com.mds.game.Game;
import com.mds.game.map.objects.ObjectMap;
import com.mds.game.map.objects.ObjectMapInterface;
import com.mds.game.util.Segment;

import java.util.ArrayList;
import java.util.List;

public class Map implements MapInterface {
    private List<ObjectMap> objectsMap;
    private int sizeX;
    private int sizeY;
    public Game game;

    public Map(int sizeX, int sizeY) {
        if(sizeX<50){
            this.sizeX = 50;
        } else this.sizeX = sizeX;
        if(sizeY<200){
            this.sizeY = 200;
        } else this.sizeY = sizeY;
        objectsMap = new ArrayList<ObjectMap>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void addObjectMap(ObjectMap objectMap){
        objectsMap.add(objectMap);
    }

    public List<ObjectMapInterface> getObjectsMap() {
        List<ObjectMapInterface> s = new ArrayList<ObjectMapInterface>(objectsMap);
        return s;
    }

    public List<ObjectMap> getObjectsInMap() { return objectsMap; }

    public void tick(float deltaTime) {
        for (ObjectMap object: objectsMap){
            object.tick(deltaTime);
        }
    }
}
