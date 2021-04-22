import biuoop.DrawSurface;

/**
 * @author 319339198
 * Blocks of the game
 */
public class Block implements Collidable, Sprite {
    private Rectangle block;
    private java.awt.Color color;

    /**
     * creates a new Block.
     * @param rectangle - the new block
     */
    public Block(Rectangle rectangle) {
        this.block = rectangle;
    }

    /**
     * creates a new Block.
     * @param rectangle - the new block
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.block = rectangle;
        this.color = color;
    }

    /**
     * creates a new Block.
     * @return this color block
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * return the block.
     * @return this block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * matrix with the velocity to change.
     * @param - collisionPoint where the collision occurred
     * @return matrix with the velocity to change
     */
    private Point velocityChange(Point collisionPoint) {
        double width = block.getWidth();
        double height = block.getHeight();
        if(collisionPoint.getX() == block.getBottomLeft().getX()
                || collisionPoint.getX() == block.getBottomRight().getX()) {
            return new Point(-1,1);
        }
        if(collisionPoint.getY() == block.getBottomLeft().getY()
                || collisionPoint.getY() == block.getUpperRight().getY()) {
            return new Point(1,-1);
        }
        return new Point(1,1);
    }

    /**
     * return a new velocity for a ball
     * @param collisionPoint - where the hit occurred
     * @param currentVelocity - current velocity of the ball
     * @return a new velocity for a ball
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Point velocityChange = velocityChange(collisionPoint);
        Velocity newVelocity = new Velocity(currentVelocity.getDx() * velocityChange.getX(),
                currentVelocity.getDy() * velocityChange.getY());
        return newVelocity;
    }

    /**
     * draw this block on the surface.
     * @param surface - the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
    }

    /**
     * what should the  block do after some time
     */
    @Override
    public void timePassed() {

    }

    /**
     * add the ball to the game.
     * @param game - the game object
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}

