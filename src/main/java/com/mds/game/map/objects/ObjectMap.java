package com.mds.game.map.objects;

import com.mds.game.map.Map;
import com.mds.game.util.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectMap implements ObjectMapInterface{
    protected int x;
    protected int y;
    protected int r;
    protected TypeCollision typeCollision;
    protected float moveX=0;
    protected float moveY=0;
    protected int nextMoveX=0;
    protected int nextMoveY=0;
    protected float normalVelocity=0;
    protected float currentVelocity=0;
    protected Vector2d vectorMove;
    protected Vector2d forwardVector=new Vector2d(1,0);
    protected Vector2d rightVector;
    protected Map map;
    protected int sizeX;
    protected int sizeY;
    protected boolean canMoved = false;
    protected TypeObject typeObject;
    protected float leanghtDiag=0;
    protected float dAngle=0;
    protected char hitChar;

    protected Coord2d coord1=new Coord2d(0,0);
    protected Coord2d coord2=new Coord2d(0,0);
    protected Coord2d coord3=new Coord2d(0,0);
    protected Coord2d coord4=new Coord2d(0,0);
    protected Coord2d nextCoord1=new Coord2d(0,0);
    protected Coord2d nextCoord2=new Coord2d(0,0);
    protected Coord2d nextCoord3=new Coord2d(0,0);
    protected Coord2d nextCoord4=new Coord2d(0,0);
    protected CrossSegmentResult crossSegmentResult;
    protected List<Coord2d> coordList=new ArrayList<Coord2d>();

    public ObjectMap(int x, int y,int sizeX, int sizeY,Map map) {
        typeCollision = TypeCollision.box;
        this.map = map;
        this.x = x;
        this.y = y;
        if (sizeX < 3) {
            this.sizeX = 3;
        } else this.sizeX = sizeX;
        if (sizeY < 3) {
            this.sizeY = 3;
        } else this.sizeY = sizeY;
        vectorMove = new Vector2d(0, 0);
        currentVelocity=normalVelocity;
        typeObject=TypeObject.object;
        updateSet();
    }
    public ObjectMap(int x, int y,int r,Map map) {
        typeCollision = TypeCollision.sphere;
        this.map = map;
        this.x = x;
        this.y = y;
        this.r=r;
        vectorMove = new Vector2d(0, 0);
        currentVelocity=normalVelocity;
        typeObject=TypeObject.object;
        updateSet();
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public Coord2d getCoord1() { return coord1.getDublicate(); }

    public Coord2d getCoord2() { return coord2.getDublicate(); }

    public Coord2d getCoord3() { return coord3.getDublicate(); }

    public Coord2d getCoord4() { return coord4.getDublicate(); }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2d getForwardVector() {
        return new Vector2d(vectorMove.getX(),vectorMove.getY());
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getR(){
        return r;
    }

    protected void updateSet(){
        if(typeCollision==TypeCollision.sphere){
            leanghtDiag=r;
            sizeY=r;
            sizeX=r;
        } else {
            float sx=sizeX/2;
            float sy=sizeY/2;
            leanghtDiag= (float) Math.sqrt(sx*sx+sy*sy);
            dAngle= (float) (Math.atan(sy/sx)*180/Math.PI);
        }
        rightVector = new Vector2d(forwardVector.getY(),-forwardVector.getX());
        coordList.add(coord1);
        coordList.add(coord2);
        coordList.add(coord3);
        coordList.add(coord4);
        updateCoordinate();
    }

    public void tick(float deltaTime) {

    }

    protected boolean nextMove(float deltaTime){
        float vSpeed = currentVelocity*deltaTime;
        float lenVec = (float) Math.sqrt((vectorMove.getX()*vectorMove.getX())+(vectorMove.getY()*vectorMove.getY()));
        moveX=moveX+lenVec*vSpeed*vectorMove.getX();
        moveY=moveY+lenVec*vSpeed*vectorMove.getY();
        float vMoveX= (float) Math.floor(moveX);
        float vMoveY= (float) Math.floor(moveY);
        if(vMoveX!=0){
            nextMoveX=x+(int)vMoveX;
            moveX=moveX-vMoveX;
            canMoved=true;
        } else nextMoveX=x;
        if(vMoveY!=0){
            nextMoveY=y+(int)vMoveY;
            moveY=moveY-vMoveY;
            canMoved=true;
        }else nextMoveY =y;
        if(canMoved){
            canMoved=false;
            updateNextCoordinate();
            return true;
        } else return false;
    }
    protected void updateNextCoordinate(){
        nextCoord1.setX(nextMoveX-sizeX);
        nextCoord1.setY(nextMoveY+sizeY);
        nextCoord2.setX(nextMoveX+sizeX);
        nextCoord2.setY(nextMoveY+sizeY);
        nextCoord3.setX(nextMoveX+sizeX);
        nextCoord3.setY(nextMoveY-sizeY);
        nextCoord4.setX(nextMoveX-sizeX);
        nextCoord4.setY(nextMoveY-sizeY);
    }
    protected void updateCoordinate(){
        coord1.setX(x-sizeX);
        coord1.setY(y+sizeY);
        coord2.setX(x+sizeX);
        coord2.setY(y+sizeY);
        coord3.setX(x+sizeX);
        coord3.setY(y-sizeY);
        coord4.setX(x-sizeX);
        coord4.setY(y-sizeY);
    }
    protected boolean checkHitMap(){
        float angle1 = (float) Math.acos(forwardVector.scolar(vectorMove));
        float angle2 = (float) Math.acos(rightVector.scolar(vectorMove));
        int a1 = (int) (angle1*180/Math.PI);
        int a2 = (int) (angle2*180/Math.PI);
        Coord2d coord2d;
        if(a1<=90){
            if(a2<=90){
                coord2d = nextCoord3;
            } else {
                coord2d = nextCoord2;
            }
        } else {
            if(a2<=90){
                coord2d = nextCoord4;
            } else {
                coord2d = nextCoord1;
            }
        }
        if(coord2d.x<=0 || coord2d.x>=map.getSizeX()) {
            hit('x',false,0);
            return true;
        } else if(coord2d.y<=0) {
            hit('y',true,1);
            return true;
        } else if(coord2d.y>=map.getSizeY()){
            hit('y',true,2);
            return true;
        }
        return false;
    }
    protected boolean checkHitObject(){
        List<ObjectMap> objects = map.getObjectsInMap();
        for(ObjectMap object:objects) {
            if (object != this) {
                Segment s1=new Segment(object.x,object.y,nextMoveX,nextMoveY);
                float l2 = s1.length();
                float ms = leanghtDiag*2 + object.leanghtDiag;
                if (ms >=l2) {
                    Vector2d v1 = s1.createVector2d();
                    v1.normalise();
                    Vector2d v2 = new Vector2d(-v1.getX(),-v1.getY());
                    float angle=vectorMove.scolar(v2);
                    if(angle>0){
                        float l1;
                        if(object.typeCollision==TypeCollision.box){
                            angle=(float) Math.acos(object.forwardVector.scolar(v1));
                            l1=object.findLenghtForAngle(angle,this);
                        }else {
                            l1=object.r;
                        }
                        l1=l1+leanghtDiag;
                        if(l2<=l1){
                        hit(hitChar,false,0);
//                            map.game.playPause();
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }
    protected float findLenghtForAngle(float angle,ObjectMap objectMap){
        int a = (int) (angle*180/Math.PI);
        a=Math.abs(a);
        a=stabAngle(a);
        float a1;
        int da= (int) dAngle;
        if(a>=da){
            a1= (float) ((90-a)*Math.PI/180);
            objectMap.hitChar='y';
            return (sizeY)/(float)Math.cos(a1);
        } else {
            a1= (float) (a*Math.PI/180);
            objectMap.hitChar='x';
            return (sizeX)/(float)Math.cos(a1);
        }
    }
    protected int stabAngle(int angle){
        while (angle>180){
            angle=angle-180;
        }
        while (angle>90){
            angle=180-angle;
        }
        return angle;
    }
    protected void hit(char a,boolean endGame,int player){

    }
    protected void move(){
        x = nextMoveX;
        y = nextMoveY;
        updateCoordinate();
    }
}
