package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;

import javax.swing.*;
import java.util.Optional;

public class DrawingClassEvents implements MouseDrawinEvents {

    private MainClass mainClass;
    private Board board;
    private Point startPoint;

    public DrawingClassEvents(MainClass mainClass, Board board) {
        this.mainClass = mainClass;
        this.board = board;
    }

    @Override
    public boolean clicked(int x, int y) {
        return false;
    }

    @Override
    public boolean pressed(int x, int y) {
        if (startPoint == null)
            startPoint = new Point(x, y);

        return true;
    }

    @Override
    public boolean released(int x, int y) {
        Optional.ofNullable(JOptionPane.showInputDialog("Please enter the class name"))
                .filter(input -> !input.isEmpty())
                .ifPresent(input -> {
                    final int width = x - startPoint.getX();
                    final int height = y - startPoint.getY();
                    try {
                        Shape shape = mainClass.createShape(
                                startPoint,
                                new Size(width, height), input);
                        board.addShape(shape);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

        return true;
    }

}
