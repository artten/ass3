import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Test {
    static final int WIDTH = 400;
    static final int HEIGHT = 450;
    static final int MAX_RADIUS = 50;
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
