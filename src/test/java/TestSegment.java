
import com.mds.game.util.CrossSegmentResult;
import com.mds.game.util.Segment;
import com.mds.game.util.Vector2d;

public class TestSegment {
    public static void main(String[] args) {
//        Segment segment1=new Segment(new Coord(1,1),new Coord(9,3));
//        Segment segment2=new Segment(new Coord(5,2),new Coord(9,3));
//        CrossSegmentResult result = segment1.crossSegment(segment2);
//        if(result.isCross()){
//            System.out.println("Crossing");
//        } else System.out.println("Error");
//        int sizeX=40;
//        int a=35;
//        float a1= (float) (a*Math.PI/180);
//        System.out.println("angle="+a);
//        System.out.println("sin="+(float)Math.sin(a1));
//        System.out.println("res="+(sizeX/2)/(float)Math.sin(a1));
        Vector2d v1 = new Vector2d(1,0);
        Vector2d v2 = new Vector2d(1,1);
        Vector2d v3 = new Vector2d(1,-1);
        v2.normalise();
        v3.normalise();
        float angle = (float) Math.acos(v1.scolar(v2));
        int a = (int) (angle*180/Math.PI);
        System.out.println("angle="+angle);
        angle = (float) Math.acos(v1.scolar(v3));
        a = (int) (angle*180/Math.PI);
        System.out.println("angle="+(10)/(float)Math.cos(8*Math.PI/180));
    }
}
