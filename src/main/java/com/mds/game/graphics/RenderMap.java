package com.mds.game.graphics;

import com.mds.game.map.Map;
import com.mds.game.map.objects.ObjectMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class RenderMap {
    private List<ObjectMap> list;
    private BufferedImage map;
    private Graphics graphics;
    public RenderMap(Map map){
       this.map=new BufferedImage(map.getSizeX(),map.getSizeY(),BufferedImage.TYPE_INT_RGB);
       graphics = this.map.getGraphics();
       list = map.getObjectsInMap();
    }

    public BufferedImage getMap() {
        return map;
    }
    public void updateMap(){
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,map.getWidth(),map.getHeight());
        for(ObjectMap objectMap:list){
            switch (objectMap.getTypeObject()){
                case ball:
                    graphics.setColor(Color.black);
                    graphics.drawOval(objectMap.getX(),objectMap.getY(),objectMap.getR(),objectMap.getR());
                    break;
                case board:
                    graphics.setColor(Color.black);
                    graphics.drawOval(objectMap.getX(),objectMap.getY(),objectMap.getSizeX(),objectMap.getSizeY());
                    break;
                case object:
                    break;
            }
        }
    }
}
