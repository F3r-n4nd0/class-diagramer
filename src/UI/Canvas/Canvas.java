package UI.Canvas;

import Domain.Board.ActionCanvas;
import Domain.Board.Board;
import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;
import javax.swing.*;

public class Canvas extends JPanel implements MouseListener, ActionCanvas {

    private Board board;
    private Optional<Point> startPoint = Optional.empty();

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
            Optional<Shape>  selectShape = this.menuDelegate.getSelectShape();
            if (!selectShape.isPresent()) {
              board.selectShape(new Point(e.getX(), e.getY()));
              repaint();
              return;
            }

            if (!(selectShape.get() instanceof MainClass)) {
                return;
            }

            MainClass mainClass = (MainClass) selectShape.get();
            Optional.ofNullable(JOptionPane.showInputDialog("Please enter the class name"))
                .filter(input -> !input.isEmpty())
                .ifPresent(input -> {
                  final Integer DEFAULT_CLASS_WIDTH = 200;
                  final Integer DEFAULT_CLASS_HEIGHT = 100;
                  try {
                    Shape shape  = mainClass.createShape(
                        new Point(e.getX(), e.getY()),
                        new Size(DEFAULT_CLASS_WIDTH, DEFAULT_CLASS_HEIGHT), input);
                    board.addShape(shape);
                  } catch (Exception e1) {
                    e1.printStackTrace();
                  }
                });

            this.menuDelegate.deselectAll();
            repaint();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Optional<Shape> selectShape = this.menuDelegate.getSelectShape();
        if (selectShape.isPresent() && selectShape.get() instanceof Connector && !startPoint.isPresent()) {
          startPoint = Optional.of(new Point(e.getX(), e.getY()));
          return;
        }

        Shape shape = board.getShape(new Point(e.getX(), e.getY()));
        if (board.isSelected(shape)) {
            startPoint = Optional.of(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Optional<Shape> selectShape = this.menuDelegate.getSelectShape();
            final Boolean isPresentShape = selectShape.isPresent();
            final Boolean isPresentStartPoint = startPoint.isPresent();

            if (!isPresentShape && isPresentStartPoint) {
                int x = e.getX() - startPoint.get().getX();
                int y = e.getY() - startPoint.get().getY();
                board.moveSelected(x, y);
                this.startPoint = Optional.empty();
                repaint();
                return;
            }

            if (!isPresentShape || !isPresentStartPoint || !(selectShape.get() instanceof Connector)) {
                return;
            }

            MainClass firstClass = board.getMainClass(startPoint.get());
            MainClass secondClass = board.getMainClass(new Point(e.getX(), e.getY()));
            Connector connector = (Connector) selectShape.get();

            if (Optional.ofNullable(firstClass).isPresent()
                && Optional.ofNullable(secondClass).isPresent()) {
              Shape shape = connector.createShape(firstClass, secondClass);
              board.addShape(shape);
              this.startPoint = Optional.empty();
              this.menuDelegate.deselectAll();
              repaint();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        board.getShapes().forEach(shape -> {
            try {
              this.selectShape(board, shape, graphics);
              DrawObjects.drawShapes(graphics, shape.getObjectsToDraw());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void repaintCanvas(){
        repaint();
    }

    private void selectShape(Board board, Shape shape, Graphics graphics) {
        if (board.isSelected(shape)) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.BLACK);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}