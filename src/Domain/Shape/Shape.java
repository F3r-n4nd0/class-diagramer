package Domain.Shape;

import Domain.Shape.Models.Point;
import Domain.Shape.Models.Size;

public interface Shape {

    ObjectsToDraw getObjectsToDraw() throws Exception;

    ObjectsToDraw getObjectsToDraw(Point position, Size size) throws Exception;
}
