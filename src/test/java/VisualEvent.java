import com.mds.game.Game;
import com.mds.game.GameInterface;
import com.mds.game.VisualEventInterface;
import org.junit.Test;

public class VisualEvent implements VisualEventInterface {
    private TestApp app;
    private GameInterface game;
    private Starter starter;

    public VisualEvent(TestApp app) {
        this.app = app;
        starter = new Starter();
    }

    @Override
    public void endGame(int Player) {

    }

    @Override
    public void createGame() {
        game.startCreateGame(starter);
        game.playPause();
    }

    @Override
    public void gameStart() {

    }

    @Override
    public void setGameInterface(GameInterface gameInterface) {
        this.game = gameInterface;
    }
}
