package com.mds.game.map;

import com.mds.game.Game;
import com.mds.game.map.objects.ObjectMap;
import com.mds.game.util.Segment;

import java.util.ArrayList;
import java.util.List;

public class Map implements MapInterface {
    private List<ObjectMap> objectsMap;
    private int sizeX;
    private int sizeY;
    public Game game;
    public List<Segment> segmentList = new ArrayList<Segment>();

    public Map(int sizeX, int sizeY) {
        if(sizeX<50){
            this.sizeX = 50;
        } else this.sizeX = sizeX;
        if(sizeY<200){
            this.sizeY = 200;
        } else this.sizeY = sizeY;
        objectsMap = new ArrayList<ObjectMap>();
    }

    public void tick(float deltaTime) {
        for (ObjectMap object: objectsMap){
            object.tick(deltaTime);
        }
    }

    private void updateSegment(){
        segmentList.add(new Segment(0,0,0,sizeY));
        segmentList.add(new Segment(0,sizeY,sizeX,sizeY));
        segmentList.add(new Segment(sizeX,sizeY,sizeX,0));
        segmentList.add(new Segment(sizeX,0,0,0));
    }
    public void addObjectMap(ObjectMap objectMap){
        objectsMap.add(objectMap);
    }

    public List<ObjectMap> getObjectsMap() {
        List<ObjectMap> s = new ArrayList<ObjectMap>(objectsMap);
        return s;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

}
