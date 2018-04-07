package Domain.Shape;


import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;

public abstract class Connector {

    private MainClass firstClass;
    private MainClass secondClass;
    private Line line;

    public Connector() {
    }

    public Connector(MainClass firstClass, MainClass secondClass) throws Exception {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.line = calculateShortLine();
    }

    protected Line getLine() {
        return line;
    }

    private Line calculateShortLine() throws Exception {
        Point[] unionPointsFirsClass = firstClass.getUnionPoints();
        Point[] unionPointsSecondClass = secondClass.getUnionPoints();

        return getShortLine(unionPointsFirsClass, unionPointsSecondClass);
    }

    private Line getShortLine(Point[] unionPointsFirsClass, Point[] unionPointsSecondClass) throws Exception {
        Line shortLine = null;
        for (Point pointFirst : unionPointsFirsClass) {

            for (Point pointSecond : unionPointsSecondClass) {

                Line line = new Line(pointFirst, pointSecond);
                if (shortLine == null || line.getLength() < shortLine.getLength()) {
                    shortLine = line;
                }

            }
        }
        return shortLine;
    }

    protected Point calculatePointsArrow(Point point, int arrowLength, double angleLine) {
        double px = (point.getX() + arrowLength * Math.cos(angleLine));
        double py = (point.getY() - arrowLength * Math.sin(angleLine));
        return new Point(round(px), round(py));
    }

    public int round(double val) {
        long factor = (long) Math.pow(10, 0);
        val = val * factor;
        long tmp = Math.round(val);
        return (int) (tmp / factor);
    }

    protected Point calculateMiddlePoint(Point point1, Point point2) {
        int x = (int) (point1.getX() + point2.getX()) / 2;
        int y = (int) (point1.getY() + point2.getY()) / 2;
        return new Point(x, y);
    }

}
