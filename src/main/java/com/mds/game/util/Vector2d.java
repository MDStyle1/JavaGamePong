package com.mds.game.util;

public class Vector2d {
    private float x;
    private float y;
    private Double pi= Math.PI;

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2d(float x1,float x2, float y1,float y2) {
        this.x = x2-x1;
        this.y = y2-y1;
    }
    public void rotateVector(int a){
        double angle =(a*pi/180);
        double sn;
        double cs;
//        if(angle==45){
//            cs = sn = (float) (Math.sqrt(2)/2);
//        } else {
//            cs = (float) Math.cos(angle);
//            sn = (float) Math.sin(angle);
//        }
        cs = (float) Math.cos(angle);
        sn = (float) Math.sin(angle);
        double rx = x * cs - y * sn;
        double ry = x * sn + y * cs;
//        rx=(int)(rx*100);
//        rx=rx/100;
//        ry=(int)(ry*100);
//        ry=ry/100;
        x= (float) rx;
        y= (float) ry;
    }
    public void print(){
        System.out.println("x = "+x+"  y = "+y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Segment createSegment(Coord2d coord2d, float length){
        return new Segment(coord2d,new Coord2d(x*length+coord2d.x,y*length+coord2d.y));
    }
    public Coord2d createCoord(Coord2d coord2d, float length){
        return new Coord2d(x*length+coord2d.x,y*length+coord2d.y);
    }
    public void normalise(){
        float l= lenght();
        x= x/l;
        y= y/l;
    }
    public float lenght(){
        return (float) Math.sqrt(x*x+y*y);
    }
    public float scolar(Vector2d vector2d){
        return (x*vector2d.x)+(y*vector2d.y);
    }
    public void invert(){
        x=-x;
        y=-y;
    }
    public void setVector(float x,float y){
        this.x=x;
        this.y=y;
    }
    public void reverseX(){
        x=-x;
    }
    public void reverseY(){
        y=-y;
    }
    public void reverse(){
        x=-x;
        y=-y;
    }
}
