package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;
import Domain.Shape.Shape;

import javax.swing.*;
import java.util.Optional;

public class SelectEvents implements MouseDrawinEvents {

    private Board board;

    public SelectEvents(Board board) {
        this.board = board;
    }

    @Override
    public boolean clicked(int x, int y) {
        board.selectShape(new Point(x, y));
        return true;
    }

    @Override
    public boolean pressed(int x, int y) {
        return false;
    }

    @Override
    public boolean released(int x, int y) {
        return false;
    }

}
