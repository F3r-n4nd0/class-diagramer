package Domain.Shape;


import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;

import java.io.Serializable;

public abstract class Connector implements Serializable {

    private MainClass firstClass;
    private MainClass secondClass;


    public Connector() {
    }

    public Connector(MainClass firstClass, MainClass secondClass) throws Exception {
        if (firstClass == null) {
            throw new Exception("First class can't be null");
        }
        if (secondClass == null) {
            throw new Exception("Second class can't be null");
        }
        this.firstClass = firstClass;
        this.secondClass = secondClass;
    }

    public abstract Shape createShape(MainClass firstClass, MainClass secondClass) throws Exception;

    protected Line calculateShortLine() throws Exception {
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
        long px = (long) (point.getX() + arrowLength * Math.cos(angleLine));
        long py = (long) (point.getY() - arrowLength * Math.sin(angleLine));
        return new Point(Math.round(px), Math.round(py));
    }

    protected Point calculateMiddlePoint(Point point1, Point point2) {
        int x = (point1.getX() + point2.getX()) / 2;
        int y = (point1.getY() + point2.getY()) / 2;
        return new Point(x, y);
    }

    protected boolean isLocated(Point point) {
        try {
            Line line = calculateShortLine();
            double lengthLine = line.getLength();
            double lengthLine1 = new Line(line.getStartPoint(), point).getLength();
            double lengthLine2 = new Line(line.getFinalPoint(), point).getLength();
            double lengthTotal = lengthLine1 + lengthLine2;
            return isBeetween(lengthTotal, lengthLine - 2, lengthLine + 2);
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isBeetween(double value, double first, double second) {
        return first <= value && value <= second;
    }

}
