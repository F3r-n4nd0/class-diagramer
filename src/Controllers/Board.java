package Controllers;

import Domain.Shape.Shape;

import java.util.ArrayList;

public class Board {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();

    public Board() {
    }

    public void AddShape(Shape shape) {
        shapes.add(shape);
    }



}
