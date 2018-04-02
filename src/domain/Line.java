package domain;


public class Line {

    private Point startPoint;
    private Point finalPoint;

    public Line(Point startPoint, Point finalPoint) throws Exception {

        if (startPoint == null) {
            throw new Exception("Start point can't be null");
        }
        if (finalPoint == null) {
            throw new Exception("Start point can't be null");
        }

        this.startPoint = startPoint;
        this.finalPoint = finalPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    public double getLenght() {
        return  Math.hypot(finalPoint.getX()-startPoint.getX(), finalPoint.getY()-startPoint.getY());
    }
}
