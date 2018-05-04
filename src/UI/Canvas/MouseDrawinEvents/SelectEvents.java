package UI.Canvas.MouseDrawinEvents;

import Domain.Board.Board;
import Domain.Shape.Models.Point;

public class SelectEvents implements MouseDrawingEvents {

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
