package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.Connector;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;

import javax.swing.*;
import java.util.Optional;

public class DrawingConnectorEvents implements MouseDrawinEvents {

    private Connector connector;
    private Board board;
    private Point startPoint;

    public DrawingConnectorEvents(Connector connector, Board board) {
        this.connector = connector;
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
        return false;
    }

    @Override
    public boolean released(int x, int y) {
        try {

            Optional<MainClass> firstClass = Optional.ofNullable(board.getMainClass(startPoint));
            Optional<MainClass> secondClass = Optional.ofNullable(board.getMainClass(new Point(x,y)));
            if (firstClass.isPresent() && secondClass.isPresent()) {
                Shape shape = connector.createShape(firstClass.get(), secondClass.get());
                board.addShape(shape);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

}
