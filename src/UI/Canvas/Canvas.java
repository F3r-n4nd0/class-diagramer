package UI.Canvas;

import Domain.Board.ActionCanvas;
import Domain.Board.Board;
import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.*;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Polygon;
import Domain.Shape.ObjectsToDraw;
import Domain.Shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class Canvas extends JPanel implements MouseListener, ActionCanvas {

    private Board board;
    private Point startPoint;

    private MenuShapesDelegate menuDelegate;

    public Canvas(Board board, MenuShapesDelegate delegate) {
        this.board = board;
        this.board.setDelegateCanvas(this);
        this.menuDelegate = delegate;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Shape selectShape = this.menuDelegate.getSelectShape();
            if (selectShape != null && selectShape instanceof MainClass) {

                MainClass mainClass = (MainClass) selectShape;
                String input = JOptionPane.showInputDialog("Please enter the class name");
                if (input != null && input.length() > 0) {
                    Shape shape = mainClass.createShape(
                            new Point(e.getX(), e.getY()),
                            new Size(200, 100), input);
                    board.addShape(shape);
                }
                this.menuDelegate.deselectAll();
                repaint();
            } else {
                board.selectShape(new Point(e.getX(), e.getY()));
                repaint();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Shape selectShape = this.menuDelegate.getSelectShape();
        if (selectShape != null) {
            if (selectShape instanceof Connector && startPoint == null) {
                startPoint = new Point(e.getX(), e.getY());
            }
        } else {
            Shape shape = board.getShape(new Point(e.getX(), e.getY()));
            if (board.isSelected(shape)) {
                startPoint = new Point(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Shape selectShape = this.menuDelegate.getSelectShape();
            if (selectShape == null && startPoint != null) {
                int x = e.getX() - startPoint.getX();
                int y = e.getY() - startPoint.getY();
                board.moveSelected(x, y);
                this.startPoint = null;
                repaint();

            }
            if (selectShape != null && startPoint != null && selectShape instanceof Connector) {

                Connector connector = (Connector) selectShape;
                MainClass firstClass = board.getMainClass(startPoint);
                MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
                if (firstClass != null && secondClass != null) {
                    Shape shape = connector.createShape(firstClass, secondClass);
                    board.addShape(shape);
                    this.startPoint = null;
                    this.menuDelegate.deselectAll();
                    repaint();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            for (Shape shape : board.getShapes()) {

                if (board.isSelected(shape)) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.BLACK);
                }

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

    public void repaintCanvas() {
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}