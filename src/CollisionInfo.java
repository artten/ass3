/**
 * @author 319339198
 * saves the colition info.
 */
public class CollisionInfo {
    private Point collision;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collision - collision point
     * @param collisionObject - the object to collide to
     */
    public CollisionInfo(Point collision, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collision = collision;
    }

    /**
     * return the collision point.
     * @return the collision point
     */
    public Point collisionPoint() {
        return collision;
    }

    /**
     * return the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}