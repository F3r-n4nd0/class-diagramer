package domain;

import java.util.ArrayList;

public class InterfaceClass extends ObjectClass {


    public InterfaceClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    @Override
    public ArrayList<Line> drawLines() throws Exception {
        ArrayList<Line> lines = super.drawLines();

        Point position = getPositionPoint();

        Point point1 = new Point(position.getX() + getLengthX(80), position.getY() - getLengthY(10));
        Point point2 = new Point(position.getX() + getLengthX(90), position.getY() - getLengthY(10));
        Point point3 = new Point(position.getX() + getLengthX(90), position.getY() + getLengthY(10));
        Point point4 = new Point(position.getX() + getLengthX(80), position.getY() + getLengthY(10));

        lines.add(new Line(point1, point2));
        lines.add(new Line(point2, point3));
        lines.add(new Line(point3, point4));
        lines.add(new Line(point4, point1));

        return lines;
    }

    @Override
    public ArrayList<Text> drawText() throws Exception {
        ArrayList<Text> texts = super.drawText();

        Point position = getPositionPoint();
        Point point = new Point(position.getX() + getLengthX(85), position.getY() );
        texts.add(new Text(point, "I"));

        return texts;
    }

}
