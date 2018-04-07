package UI.Canvas;

import Domain.Shape.Shape;

public interface CanvasDelegate {

    Shape getSelectShape();

    void deselectAll();
}
