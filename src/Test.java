import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class Test {
    static final int WIDTH = 400;
    static final int HEIGHT = 450;
    static final int MAX_RADIUS = 50;
    public static void main(String[] args) {
        Ball ball =  new Ball(new Point(50, 50), 3, java.awt.Color.BLACK);
        ball.setVelocity(0.5, 0.5);
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        GameEnvironment gameEnvironment = new GameEnvironment();
        gameEnvironment.addCollidable(new Block(new Rectangle(new Point(55,70),400,200)));
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep(new Point(ball.getX(), ball.getY()), gameEnvironment);
            d.fillRectangle(55,70,400,200);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

}
