package UI;

import Domain.Shape.Classes.AbstractClass;
import Domain.Shape.Classes.InterfaceClass;
import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Connectors.Composition;
import Domain.Shape.Connectors.DirectAssociation;
import Domain.Shape.Connectors.Inherit;
import Domain.Shape.Models.*;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Polygon;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ClassDiagrammerTest extends JPanel {


    private ArrayList<Shape> shapes;


    public ClassDiagrammerTest(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    private static void createAndShowUI() {

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        try {
            NormalClass nc = new NormalClass(new Point(100, 100), new Size(200, 100), "Class NormalClass");
            shapes.add(nc);

            InterfaceClass nc2 = new InterfaceClass(new Point(100, 300), new Size(200, 100), "Class InterfaceClass");
            shapes.add(nc2);

            AbstractClass nc3 = new AbstractClass(new Point(400, 300), new Size(200, 100), "Class AbstractClass");
            shapes.add(nc3);

            AbstractClass nc4 = new AbstractClass(new Point(150, 500), new Size(200, 100), "Class AbstractClass 2");
            shapes.add(nc4);

            Composition conn = new Composition(nc, nc2);
            shapes.add(conn);

            Inherit conn2 = new Inherit(nc2, nc3);
            shapes.add(conn2);

            DirectAssociation conn3 = new DirectAssociation(nc2, nc4);
            shapes.add(conn3);


        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("ClassDiagrammerTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ClassDiagrammerTest(shapes));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g.setFont(new Font("Lucida Grande", Font.BOLD, 15));

            for (Shape shape : this.shapes) {

                ObjectsToDraw ojbToDraw = shape.getObjectsToDraw();

                for (Line line : ojbToDraw.getLines()) {
                    g.drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getFinalPoint().getX(), line.getFinalPoint().getY());
                }

                for (Text text : ojbToDraw.getTexts()) {

                    FontMetrics metrics = g.getFontMetrics(g.getFont());
                    Rectangle2D rectangle = metrics.getStringBounds(text.getText(), g);

                    int textWidth = (int) Math.round(rectangle.getWidth() / 2.0);
                    int textHeight = (int) Math.round(rectangle.getHeight() / 2.0);

                    g.drawString(text.getText(), text.getStartPoint().getX() - textWidth, text.getStartPoint().getY() + textHeight);
                }

                for (Polygon polygon : ojbToDraw.getPolygons()) {
                    g.fillPolygon(polygon.getPointsX(), polygon.getPointsY(), polygon.getNumberOfPoints());
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
