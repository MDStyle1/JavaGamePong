import com.mds.game.GameInterface;
import com.mds.game.VisualEventInterface;

public class VisualEvent implements VisualEventInterface {
    private TestApp app;
    private GameInterface game;

    public VisualEvent(TestApp app) {
        this.app = app;
    }

    @Override
    public void eventEndGame(int Player) {

    }

    @Override
    public void eventCreateGame() {
        game.createMapAndStart();
    }

    @Override
    public void eventGameStart() {

    }
}
