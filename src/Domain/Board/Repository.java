package Domain.Board;

import Domain.Shape.Shape;

import java.util.ArrayList;

public interface Repository {

    void Save(ArrayList<Shape> shapes) throws Exception;

    ArrayList<Shape> Get() throws Exception;
}
