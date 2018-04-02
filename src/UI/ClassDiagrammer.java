package UI;

import domain.*;
import domain.Point;
import domain.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDiagrammer extends JPanel {


    private ArrayList<Shape> shapes;


    public ClassDiagrammer(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    private static void createAndShowUI() {

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        try {
            NormalClass nc = new NormalClass(new Point(100, 100), new Size(200, 100), "Class Normal");
            shapes.add(nc);

            InterfaceClass nc2 = new InterfaceClass(new Point(100, 300), new Size(200, 100), "Class Interface");
            shapes.add(nc2);

            AbstractClass nc3 = new AbstractClass(new Point(400, 300), new Size(200, 100), "Class Abstract");
            shapes.add(nc3);

            AbstractClass nc4 = new AbstractClass(new Point(200, 500), new Size(200, 100), "Class Abstract");
            shapes.add(nc4);

            AssociationConnector conn =  new AssociationConnector(nc, nc2);
            shapes.add(conn);

            AssociationConnector conn2 =  new AssociationConnector(nc2, nc3);
            shapes.add(conn2);

            DirectAssociationConnection conn3 =  new DirectAssociationConnection(nc2, nc4);
            shapes.add(conn3);

        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("ClassDiagrammer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ClassDiagrammer(shapes));
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

            for (Shape shape : this.shapes) {

                List<Line> lines = shape.drawLines();
                for (Line line : lines) {
                    g.drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getFinalPoint().getX(), line.getFinalPoint().getY());
                }

                List<Text> texts = shape.drawText();
                for (Text text : texts) {
                    FontMetrics metrics = g.getFontMetrics(g.getFont());
                    int textWidth = metrics.stringWidth(text.getText()) /2;
                    int textHeight = metrics.getHeight() /2 ;

                    g.drawString(text.getText(),text.getStartPoint().getX() - textWidth  , text.getStartPoint().getY() + textHeight);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
