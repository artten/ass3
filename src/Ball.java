/**
 * @author 319339198
 */

import biuoop.DrawSurface;

/**
 * one dimension ball.
 */
public class Ball implements Sprite {
    private Point center;
    private double radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor.
     * @param center - center of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(Point center, double r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor.
     * @param center - center of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     * @param gameEnvironment - gameEnvironment of the ball
     */
    public Ball(Point center, double r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor.
     * @param x - x value
     * @param y - y value
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(double x, double y, double r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * get the x value of the ball.
     * @return the x position of the ball
     */
    public double getX() {
        return (int) this.center.getX();
    }

    /**
     * get the x value of the ball.
     * @return the x position of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * get the y value of the ball.
     * @return the y position of the ball
     */
    public double getY() {
        return (int) this.center.getY();
    }

    /**
     * get the radius of the ball.
     * @return the radius of the ball
     */
    public double getSize() {
        return this.radius;
    }

    /**
     * return the ball color.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw this ball on the surface.
     * @param surface - the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) radius);
    }

    @Override
    public void timePassed() {
        moveOneStep(this.center);
    }

    /**
     * set new velocity.
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set new velocity.
     * @param dx - dx value of velocity
     * @param dy - dy value of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     *  get velocity.
     * @return this velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     *  function that makes a move with a this ball.
     * @param start - start point
     */
    public void moveOneStep(Point start) {
        Line trajectory = new Line(center, this.getVelocity().applyToPoint(this.center));
        if (gameEnvironment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
            Velocity oldVelocity = this.velocity;
            this.velocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(),
                    this.velocity);
            if (oldVelocity.getDx() != this.velocity.getDx()) {
                if (this.velocity.getDx() > 0) {
                    this.center = new Point(collisionInfo.collisionPoint().getX() + radius + 2,
                            collisionInfo.collisionPoint().getY());
                } else {
                    this.center = new Point(collisionInfo.collisionPoint().getX() - radius - 2,
                            collisionInfo.collisionPoint().getY());
                }

            }
             if (oldVelocity.getDy() != this.velocity.getDy()) {
                if (this.velocity.getDy() > 0) {
                    this.center = new Point(collisionInfo.collisionPoint().getX(),
                            collisionInfo.collisionPoint().getY() + radius + 2);
                } else {
                    this.center = new Point(collisionInfo.collisionPoint().getX(),
                            collisionInfo.collisionPoint().getY() - radius - 2);
                }

            }
        }
    }

    /**
     * check if the ball should bounce and change velocity.
     * @param height - height of surface
     * @param width - width of surface
     * @param start - start Point
     */
    public void checkBounce(Point start, int height, int width) {
        if (width == center.getX() + radius - start.getX() || 0 == center.getX() - radius - start.getX()) {
            velocity = new Velocity(-velocity.getDx(), velocity.getDy());
        }
        if (height == center.getY() + radius - start.getY() || 0 == center.getY() - radius - start.getY()) {
            velocity = new Velocity(velocity.getDx(), -velocity.getDy());
        }
    }

    /**
     * add the ball to the game.
     * @param game - the game object
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

}