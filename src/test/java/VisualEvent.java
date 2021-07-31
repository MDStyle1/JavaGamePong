import com.mds.game.GameInterface;
import com.mds.game.VisualEventInterface;

public class VisualEvent implements VisualEventInterface {
    private TestApp app;
    public GameInterface game;

    public VisualEvent(TestApp app) {
        this.app = app;
    }

    @Override
    public void eventEndGame(int Player) {

    }

    @Override
    public void eventCreateGame() {
    }

    @Override
    public void eventGameStart() {

    }
}
