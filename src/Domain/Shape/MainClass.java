package Domain.Shape;

import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;

public abstract class MainClass {

    private Point positionPoint;
    private Size size;
    private String text;

    public MainClass(Point positionPoint, Size size, String text) throws Exception {
        if (positionPoint == null) {
            throw new Exception("Position point can't be null");
        }
        if (size == null) {
            throw new Exception("Size can't be null");
        }
        if (text.isEmpty()) {
            throw new Exception("Size can't be null");
        }
        this.positionPoint = positionPoint;
        this.size = size;
        this.text = text;
    }

    public Point getPositionPoint() {
        return positionPoint;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }


    protected Point[] getUnionPoints() {

        Point point1 = new Point(positionPoint.getX() + size.getWidth(50), positionPoint.getY());
        Point point2 = new Point(positionPoint.getX(), positionPoint.getY() + size.getHeight(50));
        Point point3 = new Point(positionPoint.getX() + size.getWidth(50), positionPoint.getY() + size.getHeight());
        Point point4 = new Point(positionPoint.getX() + size.getWidth(), positionPoint.getY() + size.getHeight(50));

        return new Point[]{point1, point2, point3, point4};
    }


    public boolean isLocated(Point point) {
        return (isBeetween(point.getX(), positionPoint.getX(), (positionPoint.getX() + size.getWidth())) &&
                (isBeetween(point.getY(), positionPoint.getY(), (positionPoint.getY() + size.getHeight()))));

    }

    private boolean isBeetween(int value, int first, int second) {
        return first <= value && value <= second;
    }

}
