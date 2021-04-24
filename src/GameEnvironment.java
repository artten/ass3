/**
 * @author 319339198
 */
import java.util.LinkedList;
import java.util.List;

/**
 * creates a new game.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c - new collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - the line of the ball move
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = null;
        for (Collidable collidable : this.collidables) {
            Rectangle block = collidable.getCollisionRectangle();
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(block);
            if (closestCollision != null) {
                if (collisionInfo == null) {
                    collisionInfo = new CollisionInfo(closestCollision, collidable);
                } else {
                    if (trajectory.start().distance(closestCollision)
                            < trajectory.start().distance(collisionInfo.collisionPoint())) {
                        collisionInfo = new CollisionInfo(closestCollision, collidable);
                    }
                }
            }
        }
        return collisionInfo;
    }

}