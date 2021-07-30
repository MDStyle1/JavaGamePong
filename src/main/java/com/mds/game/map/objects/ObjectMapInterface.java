package com.mds.game.map.objects;

import com.mds.game.util.Coord2d;
import com.mds.game.util.Vector2d;

public interface ObjectMapInterface {
    int getX();
    int getY();
    int getSizeX();
    int getSizeY();
    int getR();
    Vector2d getForwardVector();
    TypeObject getTypeObject();
    Coord2d getCoord1();
    Coord2d getCoord2();
    Coord2d getCoord3();
    Coord2d getCoord4();
}
