package domain;

import java.util.ArrayList;

public class DirectAssociationConnection extends ObjectConnector {


    public DirectAssociationConnection(ObjectClass firstClass, ObjectClass secondClass) throws Exception {
        super(firstClass, secondClass);
    }


    @Override
    public ArrayList<Line> drawLines() throws Exception {

        Line line = getLine();

        double ang;
        double angSep = 25.0;
        double tx, ty;
        int dist = 10;

        ty = -(line.getStartPoint().getY() - line.getFinalPoint().getY()) * 1.0;
        tx = (line.getStartPoint().getX() - line.getFinalPoint().getX()) * 1.0;

        ang = Math.atan(ty / tx);

        if (tx < 0) {
            ang += Math.PI;
        }

        int p1x = (int) (line.getFinalPoint().getX() + dist * Math.cos(ang - Math.toRadians(angSep)));
        int p1y = (int) (line.getFinalPoint().getY() - dist * Math.sin(ang - Math.toRadians(angSep)));
        int p2x = (int) (line.getFinalPoint().getX() + dist * Math.cos(ang + Math.toRadians(angSep)));
        int p2y = (int) (line.getFinalPoint().getY() - dist * Math.sin(ang + Math.toRadians(angSep)));


        Point point1 = new Point(p1x,p1y);
        Point point2 = new Point(p2x,p2y);


        ArrayList<Line> lines = new ArrayList<Line>();

        lines.add(line);
        lines.add(new Line(point1,line.getFinalPoint()));
        lines.add(new Line(point2,line.getFinalPoint()));


        return lines;
    }
}
