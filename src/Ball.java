/**
 * @author 319339198
 */

import biuoop.DrawSurface;

/**
 * one dimension ball.
 */
public class Ball {
    private Point center;
    private double radius;
    private java.awt.Color color;
    private Velocity velocity;

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
     * @param height - height of surface
     * @param width - width of surface
     */
    public void moveOneStep(Point start, int height, int width) {
        if (this.velocity != null) {
            Point p = this.getVelocity().applyToPoint(this.center);
            if (p.getX() - radius - start.getX() < 0) {
                p = new Point(start.getX() + radius, p.getY());
            }
            if (p.getX() + radius > width + start.getX()) {
                p = new Point(width - radius + start.getX(), p.getY());
            }
            if (p.getY() - radius - start.getX() < 0) {
                p = new Point(p.getX(), radius + start.getY());
            }
            if (p.getY() + radius > height + start.getX()) {
                p = new Point(p.getX(), height - radius +  start.getY());
            }
            this.center = p;
        }
    }

    /**
     *  function that makes a move with a this ball.
     * @param start - start point
     * @param gameEnvironment - gameEnvironment
     */
    public void moveOneStep(Point start, GameEnvironment gameEnvironment) {
        Line trajectory = new Line(center, this.getVelocity().applyToPoint(this.center));
        if(gameEnvironment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        else {
            CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
            this.center = new Point(collisionInfo.collisionPoint().getX() - radius,
                    collisionInfo.collisionPoint().getY() - radius);
            this.velocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(),
                    this.velocity);
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

}