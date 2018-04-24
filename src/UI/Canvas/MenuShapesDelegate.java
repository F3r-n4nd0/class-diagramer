package UI.Canvas;

import Domain.Shape.Shape;
import java.util.Optional;

public interface MenuShapesDelegate {

    Optional<Shape> getSelectShape();
    void deselectAll();

}
