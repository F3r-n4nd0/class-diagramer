package Domain.Board;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Shape;

import java.util.ArrayList;

public class Board {

    private ArrayList<Shape> shapes;
    private Repository repository;

    public Board() throws Exception {
        this.shapes = new ArrayList<Shape>();
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void saveData() throws Exception {
        if (repository == null) {
            throw new Exception("To save data need assign a repository");
        }
        repository.Save(shapes);
    }

    public void loadData() throws Exception {
        if (repository == null) {
            throw new Exception("To load data need assign a repository");
        }
        shapes = repository.Get();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) throws Exception {
        if (!shapes.contains(shape)) {
            throw new Exception("Board not contain this object.");
        }
        shapes.remove(shape);
    }

    public MainClass getMainClass(Point point) {

        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);

            if (shape instanceof MainClass) {
                MainClass mainClass = (MainClass) shape;
                if (mainClass.isLocated(point))
                    return (MainClass) shape;
            }
        }
        return null;
    }

    public void Undo() {
        if (shapes.isEmpty()) {
            return;
        }
        Shape shape = shapes.get(shapes.size() - 1);
        shapes.remove(shape);
    }
}
