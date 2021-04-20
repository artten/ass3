/**
 * @author 319339198
 * linear Function
 */

public class Function {
    private boolean vertical = false;
    private double slope;
    private double constant;

    /**
     * calculate the slope by two points.
     * @param one - one point on the function
     * @param two - second point that the function contains
     * @return the slope of the function
     */
    private double slope(Point one, Point two) {
        if (one.getX() - two.getX() == 0) {
            this.vertical = true;
            return 0;
        }
        return (one.getY() - two.getY()) / (one.getX() - two.getX());
    }

    /**
     * calculate the constant by the slope and onr point.
     * @param one - point that contains x and y value
     * @param slopeValue - the slope of the function
     * @return the constant of the function
     */
    private double constant(Point one, double slopeValue) {
        if (vertical) {
            return one.getY();
        }
        return (one.getY() - slopeValue * one.getX());
    }

    /**
     * constructor.
     * @param one - one point of the function.
     * @param two - second point of the function
     */
    public Function(Point one, Point two) {
        this.slope = slope(one, two);
        this.constant = constant(one, this.slope);
    }

    /**
     * return slope of the function.
     * @return the slope
     */
    public double getSlope() {
        return  this.slope;
    }

    /**
     * return the constant of the function.
     * @return the constant
     */
    public double getConstant() {
        return this.constant;
    }

    /**
     * return y value by x.
     * @param x - x value
     * @return y value
     */
    public double getYbyX(double x) {
        if (this.vertical) {
            return this.constant;
        }
        return x * this.slope + this.constant;
    }

    /**
     * return vertical value.
     * @return vertical value
     */
    public boolean isVertical() {
        return this.vertical;
    }

    /**
     * check if there is a point where other function and this function.
     * interact
     * @param other - another function
     * @return a point that contains x and y value with the interaction value
     */
    public Point functionsInteract(Function other) {
        if (this.vertical) {
            return  new Point(this.constant, other.getYbyX(this.constant));
        }
        if (other.isVertical()) {
            return  new Point(other.getConstant(), getYbyX(other.getConstant()));
        }
        double x = (other.getConstant() - this.constant) / (this.slope - other.getSlope());
        if ((int) (getYbyX(x) * 100000) == (int) (other.getYbyX(x) * 100000)) {
            return new Point(x, getYbyX(x));
        }
        return null;
    }

}
