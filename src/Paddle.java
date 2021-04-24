/**
 * @author 319339198
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;

    /**
     * constructor.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Block block) {
        this.keyboard = keyboard;
        this.block = block;
    }

    /**
     * move the paddle left
     */
    public void moveLeft() {
        Rectangle rectangle = block.getCollisionRectangle();
        Point upperLeft = rectangle.getUpperLeft();
        this.block = new Block(new Rectangle(new Point(upperLeft.getX() - 5, upperLeft.getY()),
                rectangle.getWidth(), rectangle.getHeight()), this.block.getColor());
    }

    /**
     * move the paddle right
     */
    public void moveRight() {
        Rectangle rectangle = block.getCollisionRectangle();
        Point upperLeft = rectangle.getUpperLeft();
        this.block = new Block(new Rectangle(new Point(upperLeft.getX() + 5, upperLeft.getY()),
                rectangle.getWidth(), rectangle.getHeight()), this.block.getColor());
    }

    /**
     *  draw the paddle
     * @param d - the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
    }

    /**
     * do something after time passes
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * return the rectangle
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * change velocity after thr hit
     */
    public int hitPart(Point collisionPoint) {
        int hitPart = (int) Math.abs((this.block.getCollisionRectangle().getUpperLeft().getX() - collisionPoint.getX()));
        int ok = (int) (this.block.getCollisionRectangle().getWidth())/5;
        hitPart =  (hitPart / ok) + 1;
        return  hitPart;
    }

    /**
     * change velocity after thr hit
     * @param collisionPoint - where was the it
     * @param currentVelocity - current velocity
     * @return
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        int hitPart = hitPart(collisionPoint);
        if (hitPart == 1) {
            return  Velocity.fromAngleAndSpeed(210,5);
        }
        if (hitPart == 2) {
            return  Velocity.fromAngleAndSpeed(240,5);
        }
        if (hitPart == 3) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (hitPart == 4) {
            return  Velocity.fromAngleAndSpeed(300,5);
        }
        if (hitPart == 5) {
            return  Velocity.fromAngleAndSpeed(330,5);
        }
        Velocity velocity = new Velocity(1, 1);
        return velocity;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}