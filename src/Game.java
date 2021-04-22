/**
 * @author 319339198
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

/**
 * the Game
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    /**
     * add a new collidable.
     * @param c - collidable objective
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add a new sprite.
     * @param s - sprite objective
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     *
     */
    public Game() {
        this.gui = new GUI("Cool Game", WIDTH, HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }
    /**
     * initialize the game.
     */
    public void initialize() {

        java.awt.Color color = Color.blue;
        Block block = new Block(new Rectangle(new Point(0,0),WIDTH,30), color);
        block.addToGame(this);
        block = new Block(new Rectangle(new Point(0,0),30,HEIGHT), color);
        block.addToGame(this);
        block = new Block(new Rectangle(new Point(770,0),30,HEIGHT), color);
        block.addToGame(this);
        block = new Block(new Rectangle(new Point(0,570),WIDTH,30), color);
        block.addToGame(this);
        Ball ball =  new Ball(new Point(350, 500), 5, java.awt.Color.BLACK, this.environment);
        ball.setVelocity(-3,5);
        ball.addToGame(this);
    }

    /**
     * run the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}