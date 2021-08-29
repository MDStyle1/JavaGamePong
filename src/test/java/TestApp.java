import com.mds.game.Main;
import com.mds.game.MainInterface;

public class TestApp {
    public static void main(String[] args) {
        TestApp testApp = new TestApp();
    }

    public TestApp() {
        start();
    }

    private void start(){
        MainInterface mainInterface = Main.createMain();
        mainInterface.getScoresTop10();
    }
}
