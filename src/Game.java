/**
 * @author 319339198
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

/**
 * the Game
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
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
        this.keyboard =  gui.getKeyboardSensor();
    }

    /**
     * initialize the game the balls for the game.
     * @param num -number of balls
     * @param startPoint - the start point of the paddle
     * @param width - with of the paddle
     */
    public void initializeBalls(int num, Point startPoint, int width) {
        for (int i = 0; i < num; i++ ) {
            Random rn = new Random();
            int startWidth = rn.nextInt(width) + 1;
            Point newPoint = new Point((int) startPoint.getX() + startWidth, (int) startPoint.getY());
            Ball ball =  new Ball(newPoint , 6, Color.BLACK, this.environment);
            ball.setVelocity(0,5);
            ball.addToGame(this);
        }
    }

    /**
     * initialize the the border for the game.
     * @param size - size of the blocks
     * @param color - color fpr the blocks
     */
    public void initializeBorder(int size, java.awt.Color color) {
        for (int i = 0; i < 4; i++ ) {
            Block block = new Block(new Rectangle(new Point(0,0),WIDTH,size), color);
            block.addToGame(this);
            block = new Block(new Rectangle(new Point(0,0),size,HEIGHT), color);
            block.addToGame(this);
            block = new Block(new Rectangle(new Point(WIDTH - size,0),size,HEIGHT), color);
            block.addToGame(this);
            block = new Block(new Rectangle(new Point(0,HEIGHT - size),WIDTH,size), color);
            block.addToGame(this);
        }
    }

    /**
     *
     * @param raws - num of raws
     * @param width - width of a block
     * @param height - height of one block
     * @param shift - shift from the top and left sides
     */
    public void initializeBlocks(int raws, int width, int height,int shift) {
        Block block;
        for (int i = 0; i < raws; i++ ) {
            Random rand = new Random();
            int numOfBlocks = rand.nextInt((WIDTH - shift)/width) + 1;
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            for (int j = 0; j < numOfBlocks; j++) {
                block = new Block(new Rectangle(new Point(shift + j * width,shift + (height * (i+1)) + height * 5),width,height), randomColor);
                block.addToGame(this);
            }
        }
    }

    /**
     * initialize the game.
     */
    public void initialize() {
        java.awt.Color color = Color.red;
        Block paddleBlock = new Block(new Rectangle(new Point(400,570),50,10), color);
        Paddle paddle = new Paddle(this.keyboard, paddleBlock);
        paddle.addToGame(this);
        color = Color.blue;
        initializeBlocks(6, 50, 30, 20);
        initializeBorder(20, Color.GRAY);
        initializeBalls(2, new Point(400, 440), 50);
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