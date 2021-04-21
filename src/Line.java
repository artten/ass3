/**
 * @author 319339198
 * one dimensional line
 */

public class Line {
    private Point start;
    private Point end;

    /**
     * constructor with given Points.
     * @param start - point one to start from
     * @param end - point two to end with
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor given x and y value.
     * @param x1 - x value for point one
     * @param y1 - y value for point one
     * @param x2 - x value for point two
     * @param y2 - y value for point two
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the distance between start point and end point.
     * @return the distance between start point and end point
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the x and y value of a Point in the middle of start and end points.
     * @return a Point in the middle of start and end points
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                            (this.end.getY() + this.start.getY()) / 2);
    }

    /**
     *  give the start point of the line.
     * @return  the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     *  give the end point of the line.
     * @return  the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if x is in the line.
     * @param x - the x value to check
     * @return true if x is in the line else return false
     */
    public boolean xInTheLine(double x) {
        if ((x <= this.start.getX() && x >= this.end.getX())
                || (x >= this.start.getX() && x <= this.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * check if x is in the line.
     * @param y - the x value to check
     * @return true if x is in the line else return false
     */
    public boolean yInTheLine(double y) {
        if ((y <= this.start.getY() && y >= this.end.getY())
                || (y >= this.start.getY() && y <= this.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * check if the two line interact.
     * @param other - another line to compare
     * @return true if the lines interact
     */
    public boolean isIntersecting(Line other) {
        Function thisFunc = new Function(this.start, this.end);
        Function otherFunc = new Function(other.start, other.end);
        Point newPoint = thisFunc.functionsInteract(otherFunc);
        if (newPoint != null && this.xInTheLine(newPoint.getX())
                && other.xInTheLine(newPoint.getX())
                && this.yInTheLine(newPoint.getY())
                && other.yInTheLine(newPoint.getY())) {
            return true;
        }
        return false;
    }

    /**
     * return Point with the x and y value of the interaction.
     * @param other - line to check if this line interact with
     * @return Point with coordinates of the interaction
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            Function thisFunc = new Function(this.start, this.end);
            Function otherFunc = new Function(other.start, other.end);
            return thisFunc.functionsInteract(otherFunc);
        }
        return null;
    }

    /**
     * return true is the lines are equal, false otherwise.
     * @param other - other line to compare to
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other.start == this.start
            && other.end == this.end) {
            return true;
        }
        return false;
    }

    /**
     * find the closest point that interact with the given rectangle.
     * @param rect - rectangle
     * @return closest point that interact with the rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point point = null;

        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (!intersections.isEmpty()) {
            point = intersections.get(0);
            for (int i = 0; i < intersections.size(); i++) {
                if (this.start.distance(intersections.get(i)) < this.start.distance(point)) {
                    point = intersections.get(i);
                }
            }
        }
        return point;
    }

}