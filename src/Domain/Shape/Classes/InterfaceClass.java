package Domain.Shape.Classes;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Line;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Models.Text;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class InterfaceClass extends MainClass implements Shape {

    public static final String TEXT_TYPE_CLASS = "I";
    public static final int PERCENTAGES_START_DETAIL_SQUARE = 80;
    public static final int PERCENTAGE_FINISH_DETAIL_SQUARE = 96;

    public InterfaceClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    public InterfaceClass(String text) throws Exception {
        super(new Point(0, 0), new Size(0, 0), text);
    }

    @Override
    public ObjectsToDraw getObjectsToDraw() throws Exception {
        return getObjectsToDraw(getPositionPoint(), getSize());
    }

    @Override
    public ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception {

        Point point1 = new Point(position.getX(), position.getY());
        Point point2 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY());
        Point point3 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY());
        Point point4 = new Point(position.getX() + size.getWidth(), position.getY());
        Point point5 = new Point(position.getX() + size.getWidth(), position.getY() + size.getHeight());
        Point point6 = new Point(position.getX(), position.getY() + size.getHeight());

        List<Line> lines = new ArrayList<Line>();
        lines.add(new Line(point1, point2));
        lines.add(new Line(point3, point4));
        lines.add(new Line(point4, point5));
        lines.add(new Line(point5, point6));
        lines.add(new Line(point6, point1));

        int widthHalfLittleSquare = size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE - PERCENTAGES_START_DETAIL_SQUARE) / 2;

        Point pointAbstract1 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY() - widthHalfLittleSquare);
        Point pointAbstract2 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY() - widthHalfLittleSquare);
        Point pointAbstract3 = new Point(position.getX() + size.getWidth(PERCENTAGE_FINISH_DETAIL_SQUARE), position.getY() + widthHalfLittleSquare);
        Point pointAbstract4 = new Point(position.getX() + size.getWidth(PERCENTAGES_START_DETAIL_SQUARE), position.getY() + widthHalfLittleSquare);

        lines.add(new Line(pointAbstract1, pointAbstract2));
        lines.add(new Line(pointAbstract2, pointAbstract3));
        lines.add(new Line(pointAbstract3, pointAbstract4));
        lines.add(new Line(pointAbstract4, pointAbstract1));

        int percentageCenterLittleSquare = PERCENTAGES_START_DETAIL_SQUARE + ((PERCENTAGE_FINISH_DETAIL_SQUARE - PERCENTAGES_START_DETAIL_SQUARE) / 2);

        List<Text> texts = new ArrayList<Text>();
        texts.add(new Text(new Point(position.getX() + size.getHalfWidth(), position.getY() + size.getHalfHeight()), getText()));
        texts.add(new Text(new Point(position.getX() + size.getWidth(percentageCenterLittleSquare), position.getY()), TEXT_TYPE_CLASS));

        return new ObjectsToDraw(lines, null, texts);
    }

}
