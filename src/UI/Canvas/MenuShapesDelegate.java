package UI.Canvas;

import Domain.Board.Board;
import Domain.Shape.Shape;
import UI.Canvas.MouseDrawinEvents.MouseDrawinEvents;

import java.util.Optional;

public interface MenuShapesDelegate {

    MouseDrawinEvents getMouseEvent(Board board);
    void deselectAll();

}
