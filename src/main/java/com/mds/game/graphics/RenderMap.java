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
    private int width;
    private int height;
    public RenderMap(Map map){
       this.map=new BufferedImage(map.getSizeX(),map.getSizeY(),BufferedImage.TYPE_INT_RGB);
       graphics = this.map.getGraphics();
       list = map.getObjectsInMap();
       width=this.map.getWidth();
       height=this.map.getHeight();
    }

    synchronized public BufferedImage getMap() {
        BufferedImage bufferedImage = new BufferedImage(map.getWidth(),map.getHeight(),BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(map,0,map.getHeight(),map.getWidth(),-map.getHeight(),null);
        return bufferedImage;
    }
    synchronized public void updateMap(){
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,map.getWidth(),map.getHeight());
        for(ObjectMap objectMap:list){
            switch (objectMap.getTypeObject()){
                case ball:
                    graphics.setColor(Color.black);
                    graphics.drawOval(objectMap.getX()-objectMap.getSizeX(),objectMap.getY()-objectMap.getSizeY(),objectMap.getR()*2,objectMap.getR()*2);
                    break;
                case board:
                    graphics.setColor(Color.black);
                    graphics.drawRect(objectMap.getX()-objectMap.getSizeX(),objectMap.getY()-objectMap.getSizeY(),objectMap.getSizeX()*2,objectMap.getSizeY()*2);
                    break;
                case object:
                    break;
            }
        }
    }
}
