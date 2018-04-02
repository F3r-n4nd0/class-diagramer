package domain;


import java.util.ArrayList;

public abstract class ObjectConnector implements Shape {

    private ObjectClass firstClass;
    private ObjectClass secondClass;
    private Line line;

    public ObjectConnector(ObjectClass firstClass, ObjectClass secondClass) throws Exception {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.line = calculateShortLine();
    }

    protected Line getLine() {
        return line;
    }

    private Line calculateShortLine() throws Exception {
        ArrayList<Point> unionPointsFirsClass = firstClass.getUnionPoints();
        ArrayList<Point> unionPointsSecondClass = secondClass.getUnionPoints();

       return getShortLine(unionPointsFirsClass,unionPointsSecondClass);
    }

    private Line getShortLine(ArrayList<Point> unionPointsFirsClass,ArrayList<Point> unionPointsSecondClass) throws Exception {
        Line shortLine = null;
        for (Point pointFirst : unionPointsFirsClass) {

            for (Point pointSecond : unionPointsSecondClass) {
                Line line = new Line(pointFirst, pointSecond);
                if (shortLine == null || line.getLenght() < shortLine.getLenght()) {
                    shortLine = line;
                }

            }
        }
        return shortLine;
    }

    @Override
    public ArrayList<Line> drawLines() throws Exception {
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(line);
        return lines;
    }



    @Override
    public ArrayList<Text> drawText() throws Exception {
        return new ArrayList<Text>();
    }
}
