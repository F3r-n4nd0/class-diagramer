package Domain.Shape.Connectors;

import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Association extends Connector implements Shape {

    public Association(MainClass firstClass, MainClass secondClass) throws Exception {
        super(firstClass, secondClass);
    }

    public Association() {
        super();
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        List<Line> lines = new ArrayList<Line>();
        lines.add(getLine());

        return new ObjectsToDraw(lines, null, null);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {
        Line line = new Line(
                new Point(position.getX(), position.getY() + size.getHeight() / 2),
                new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight() / 2)
        );

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        return new ObjectsToDraw(lines, null, null);
    }
}
