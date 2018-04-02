package domain;

import java.util.ArrayList;

public abstract class ObjectClass implements Shape {


    private Point positionPoint;
    private Size size;
    private String text;

    public ObjectClass(Point positionPoint, Size size, String text) throws Exception {
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

    public String getText() {
        return text;
    }

    protected int getLengthX(int percentage) {
        Size size = getSize();
        double length = size.getWidth() * (percentage / (double) 100);
        return (int) length;
    }

    protected int getLengthY(int percentage) {
        Size size = getSize();
        double length = size.getHeight() * (percentage / (double) 100);
        return (int) length;
    }

    protected ArrayList<Point> getUnionPoints() {
        ArrayList<Point> unionPoints = new ArrayList<Point>();

        Point point1 = new Point(positionPoint.getX() + getLengthX(50), positionPoint.getY());
        Point point2 = new Point(positionPoint.getX(), positionPoint.getY() + getLengthY(50));
        Point point3 = new Point(positionPoint.getX() + getLengthX(50), positionPoint.getY() + size.getHeight());
        Point point4 = new Point(positionPoint.getX() + size.getWidth(), positionPoint.getY() + getLengthY(50));

        unionPoints.add(point1);
        unionPoints.add(point2);
        unionPoints.add(point3);
        unionPoints.add(point4);

        return unionPoints;
    }

    @Override
    public ArrayList<Line> drawLines() throws Exception {
        ArrayList<Line> lines = new ArrayList<Line>();

        Size size = getSize();

        Point point1 = new Point(positionPoint.getX() , positionPoint.getY() );
        Point point2 = new Point(positionPoint.getX() + size.getWidth(),  positionPoint.getY() );
        Point point3 = new Point(positionPoint.getX() + size.getWidth() , positionPoint.getY() + size.getHeight());
        Point point4 = new Point(positionPoint.getX() , positionPoint.getY() + size.getHeight() );

        lines.add(new Line(point1, point2));
        lines.add(new Line(point2, point3));
        lines.add(new Line(point3, point4));
        lines.add(new Line(point4, point1));

        return lines;
    }

    @Override
    public ArrayList<Text> drawText() throws Exception {
        ArrayList<Text> texts = new ArrayList<Text>();
        Point position = getPositionPoint();
        Point point = new Point(position.getX() + getLengthX(50), position.getY() + getLengthY(50));
        texts.add(new Text(point, getText()));
        return texts;
    }
}
