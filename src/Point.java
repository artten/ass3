/**
 * @author 319339198
 * one dimensional point
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * @param x - this is the x value of the point
     * @param y - this is the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distane between a given point and this point.
     * @param other - another point
     * @return - return the distance of this point to the other point
     */

    public double distance(Point other) {
        return Math.sqrt(Math.pow(Math.abs(this.x - other.getX()), 2)
                + Math.pow(Math.abs(this.y - other.getY()), 2));
    }

    /**
     * check if the given point equals to the given point.
     * @param other - another point
     * @return - true if the points are equal else return false
     */
    public boolean equals(Point other) {
        if (other.getY() == this.y
            && other.getX() == this.x) {
            return true;
        }
        return false;
    }

    /**
     * return x value.
     * @return this.x
     */
    public double getX() {
        return this.x;
    }

    /**
     * return y value.
     * @return this.y
     */
    public double getY() {
        return this.y;
    }
}