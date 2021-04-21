/**
 * @author 319339198
 */

import java.util.LinkedList;
import java.util.List;

/**
 * creates a new rectangle
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.

    /**
     * creates a new rectangle.
     * @param upperLeft - start of the rectangle
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
    }

    /**
     * check if line and rectangle intersect
     * @param line - the line to check with
     * @return list of point that intersect with the line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point point = null;
        Line x1 = new Line(this.getUpperLeft(), this.getUpperRight());
        Line x2 = new Line(this.getBottomLeft(), this.getBottomRight());
        Line y1 = new Line(this.getUpperLeft(), this.getBottomLeft());
        Line y2 = new Line(this.getUpperRight(), this.getUpperRight());

        java.util.LinkedList<Point> intersections = new LinkedList<>();

        if (line.isIntersecting(x1)) {
            intersections.add(line.intersectionWith(x1));
        }

        if (line.isIntersecting(x2)) {
            intersections.add(line.intersectionWith(x2));
        }

        if (line.isIntersecting(y1)) {
            intersections.add(line.intersectionWith(y1));
        }

        if (line.isIntersecting(y2)) {
            intersections.add(line.intersectionWith(y2));
        }
        return intersections;
    }

    /**
     * return width.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * return height.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * return start point of the rectangle.
     * @return upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * return upperRight point of the rectangle.
     * @return upperRight
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + this.width, upperLeft.getY());
    }

    /**
     * return bottomLeft point of the rectangle.
     * @return bottomLeft
     */
    public Point getBottomLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + this.height);
    }

    /**
     * return bottomRight point of the rectangle.
     * @return bottomRight
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
    }
}