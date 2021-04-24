/**
 * @author 319339198
 */

public class Ass3Game {
    static final int WIDTH = 400;
    static final int HEIGHT = 450;
    static final int MAX_RADIUS = 50;

    /**
     * main.
     * @param args - main
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
