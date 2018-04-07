package Domain.Shape.Connectors;

import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;

public class Inherit extends Connector implements Shape {

    public static final int ARROW_LENGTH = 12;
    public static final int ARROW_ANGLE = 25;

    public Inherit(MainClass firstClass, MainClass secondClass) throws Exception {
        super(firstClass, secondClass);
    }

    public Inherit() {
        super();
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {

        Line line = getLine();
        double angleLine = line.getAngle();

        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculateMiddlePoint(point1, point2);

        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(new Line(line.getStartPoint(), point3));
        lines.add(new Line(point1, line.getFinalPoint()));
        lines.add(new Line(point2, line.getFinalPoint()));
        lines.add(new Line(point1, point2));

        return new ObjectsToDraw(lines, null, null);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
        Line line = new Line(
                new Point(position.getX(), position.getY() + size.getHeight() / 2),
                new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight() / 2)
        );

        double angleLine = line.getAngle();

        Point point1 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine - Math.toRadians(ARROW_ANGLE));
        Point point2 = calculatePointsArrow(line.getFinalPoint(), ARROW_LENGTH, angleLine + Math.toRadians(ARROW_ANGLE));
        Point point3 = calculateMiddlePoint(point1, point2);

        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(new Line(line.getStartPoint(), point3));
        lines.add(new Line(point1, line.getFinalPoint()));
        lines.add(new Line(point2, line.getFinalPoint()));
        lines.add(new Line(point1, point2));

        return new ObjectsToDraw(lines, null, null);
    }
}
