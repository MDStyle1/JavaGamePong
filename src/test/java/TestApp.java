import com.mds.game.Game;
import com.mds.game.GameInterface;
import com.mds.game.Main;
import com.mds.game.VisualEventInterface;
import com.mds.game.controller.PlayerControllerInterface;
import com.mds.game.map.MapInterface;

public class TestApp {
    public static void main(String[] args) {
        TestApp testApp = new TestApp();
    }

    public TestApp() {
        start();
    }

    private void start(){
        VisualEventInterface vis=new VisualEvent(this);
        Main main= new Main();
        GameInterface game = main.getGame();
//        game.setVisualEventInterface(vis);
        game.createMapAndStart();
        game.playPause();
    }
}
