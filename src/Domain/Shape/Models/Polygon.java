package Domain.Shape.Models;


import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private Point[] points;

    public Polygon(Point[] points) throws Exception {

        if (points == null) {
            throw new Exception("Points can't be null");
        }
        if (points.length <= 0) {
            throw new Exception("Points length can't be 0");
        }

        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public int getNumberOfPoints() {
        return points.length;
    }

    public int[] getPointsX() {
        List<Integer> xPoints = new ArrayList<Integer>();
        for (Point point : points) {
            xPoints.add(point.getX());
        }
        return xPoints.stream().mapToInt(i -> i).toArray();
    }

    public int[] getPointsY() {
        List<Integer> yPoints = new ArrayList<Integer>();
        for (Point point : points) {
            yPoints.add(point.getY());
        }
        return yPoints.stream().mapToInt(i -> i).toArray();
    }
}
