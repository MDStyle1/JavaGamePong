package com.mds.game.util;

public class Segment {
    private Coord2d c1;
    private Coord2d c2;

    public Segment(int x1, int y1, int x2, int y2) {
        c1=new Coord2d(x1,y1);
        c2=new Coord2d(x2,y2);
    }
    public Segment(int x1, int y1, Coord2d coord2) {
        c1=new Coord2d(x1,y1);
        c2=coord2;
    }
    public Segment(Coord2d c1, Coord2d c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public CrossSegmentResult crossSegment(Segment segment){
        int x1= (int) c1.x;
        int x2= (int) c2.x;
        int x3= (int) c1.x;
        int x4= (int) c2.x;
        int y1=(int) c1.y;
        int y2=(int) c2.y;
        int y3=(int) c1.y;
        int y4=(int) c2.y;
        int v1, v2, v3, v4;
        int xv12, xv13, xv14, xv31, xv32, xv34, yv12, yv13, yv14, yv31, yv32, yv34;
        xv12 = x2 - x1;
        xv13 = x3 - x1;
        xv14 = x4 - x1;
        yv12 = y2 - y1;
        yv13 = y3 - y1;
        yv14 = y4 - y1;
        xv31 = x1 - x3;
        xv32 = x2 - x3;
        xv34 = x4 - x3;
        yv31 = y1 - y3;
        yv32 = y2 - y3;
        yv34 = y4 - y3;
        v1 = xv34 * yv31 - yv34 * xv31;
        v2 = xv34 * yv32 - yv34 * xv32;
        v3 = xv12 * yv13 - yv12 * xv13;
        v4 = xv12 * yv14 - yv12 * xv14;
        if(v1*v2<=0&&v3*v4<=0){
            int A1, B1, C1, A2, B2, C2;
            A1 = y2 - y1;
            A2 = y4 - y3;
            B1 = x1 - x2;
            B2 = x3 - x4;
            C1 = (x1 * (y1 - y2) + y1 * (x2 - x1)) * (-1);
            C2 = (x3 * (y3 - y4) + y3 * (x4 - x3)) * (-1);

            double D = (double) ((A1 * B2) - (B1 * A2));
            double Dx = (double) ((C1 * B2) - (B1 * C2));
            double Dy = (double) ((A1 * C2) - (C1 * A2));
            int xr=(int)(Dx/D);
            int yr=(int)(Dy/D);
            return new CrossSegmentResult(xr,yr,true);
        } else {
            return new CrossSegmentResult(0,0,false);
        }
    }
    public float length(){
        return (float) Math.sqrt((c1.x-c2.x)*(c1.x-c2.x)+(c1.y-c2.y)*(c1.y-c2.y));
    }
    public void print(){
        System.out.println("x1 = "+c1.x+"  y1 = "+c1.y+"  x2 = "+c2.x+"  y2 = "+c2.y);
    }
    public Vector2d createVector2d(){
        return new Vector2d(c2.x-c1.x,c2.y-c1.y);
    }
}
