package UI.MenuBar;

import Domain.Shape.Models.*;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Polygon;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShapeButton extends JButton {

    public static final int DISTANCE_PADDING = 20;

    private Shape shape;
    public Boolean isSelected;

    public ShapeButton(Shape shape) {
        this.shape = shape;
        this.isSelected = false;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        try {
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            if (isSelected) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            Dimension dimension = getSize();
            int TotalPadding = DISTANCE_PADDING * 2;
            ObjectsToDraw ojbToDraw = shape.getObjectsToDraw(new Point(DISTANCE_PADDING, DISTANCE_PADDING), new Size(dimension.width - TotalPadding, dimension.height - TotalPadding));

            for (Line line : ojbToDraw.getLines()) {
                g.drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getFinalPoint().getX(), line.getFinalPoint().getY());
            }

            for (Polygon polygon : ojbToDraw.getPolygons()) {
                g.fillPolygon(polygon.getPointsX(), polygon.getPointsY(), polygon.getNumberOfPoints());
            }

            for (Text text : ojbToDraw.getTexts()) {

                FontMetrics metrics = g.getFontMetrics(g.getFont());
                Rectangle2D rectangle = metrics.getStringBounds(text.getText(), g);

                int textWidth = (int) Math.round(rectangle.getWidth() / 2.0);
                int textHeight = (int) Math.round(rectangle.getHeight() / 2.0);

                g.drawString(text.getText(), text.getStartPoint().getX() - textWidth, text.getStartPoint().getY() + textHeight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
