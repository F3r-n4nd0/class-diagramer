package UI.Canvas;

import Domain.Board.Board;
import Domain.Shape.Classes.AbstractClass;
import Domain.Shape.Classes.InterfaceClass;
import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Connector;
import Domain.Shape.Connectors.Association;
import Domain.Shape.Connectors.Composition;
import Domain.Shape.Connectors.DirectAssociation;
import Domain.Shape.Connectors.Inherit;
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


public class Canvas extends JPanel implements MouseListener {

    private Board board;

    private Point startPoint;
    private CanvasDelegate delegate;

    public Canvas(Board board, CanvasDelegate delegate) {
        this.board = board;
        this.delegate = delegate;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Shape selectShape = this.delegate.getSelectShape();
            if (selectShape != null) {
                if (selectShape instanceof MainClass) {
                    if (selectShape instanceof NormalClass) {
                        String input = JOptionPane.showInputDialog("Please enter the class name");
                        if (input.length() > 0) {
                            Point position = new Point(e.getX(), e.getY());
                            board.addShape(new NormalClass(position, new Size(200, 100), input));
                        }
                    }
                    if (selectShape instanceof InterfaceClass) {
                        String input = JOptionPane.showInputDialog("Please enter the class name");
                        if (input != null && input.length() > 0) {
                            Point position = new Point(e.getX(), e.getY());
                            board.addShape(new InterfaceClass(position, new Size(200, 100), input));
                        }
                    }
                    if (selectShape instanceof AbstractClass) {
                        String input = JOptionPane.showInputDialog("Please enter the class name");
                        if (input.length() > 0) {
                            Point position = new Point(e.getX(), e.getY());
                            board.addShape(new InterfaceClass(position, new Size(200, 100), input));
                        }
                    }
                    this.delegate.deselectAll();
                    repaint();
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Shape selectShape = this.delegate.getSelectShape();
        if (selectShape != null) {
            if (selectShape instanceof Connector && startPoint == null) {
                startPoint = new Point(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Shape selectShape = this.delegate.getSelectShape();
            if (selectShape != null && startPoint != null) {

                if (selectShape instanceof Connector) {
                    if (selectShape instanceof Inherit) {
                        MainClass firstClass = board.getMainClass(startPoint);
                        MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
                        if (firstClass != null && secondClass != null) {
                            board.addShape(new Inherit(firstClass, secondClass));
                        }
                    }
                    if (selectShape instanceof Association) {
                        MainClass firstClass = board.getMainClass(startPoint);
                        MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
                        if (firstClass != null && secondClass != null) {
                            board.addShape(new Association(firstClass, secondClass));
                        }
                    }
                    if (selectShape instanceof DirectAssociation) {
                        MainClass firstClass = board.getMainClass(startPoint);
                        MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
                        if (firstClass != null && secondClass != null) {
                            board.addShape(new DirectAssociation(firstClass, secondClass));
                        }
                    }
                    if (selectShape instanceof Composition) {
                        MainClass firstClass = board.getMainClass(startPoint);
                        MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
                        if (firstClass != null && secondClass != null) {
                            board.addShape(new Composition(firstClass, secondClass));
                        }
                    }
                    this.startPoint = null;
                    this.delegate.deselectAll();
                    repaint();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            for (Shape shape : board.getShapes()) {

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
