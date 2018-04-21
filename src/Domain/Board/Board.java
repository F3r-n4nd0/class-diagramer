package Domain.Board;

import Domain.Shape.MainClass;
import Domain.Shape.Models.Point;
import Domain.Shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Board {

    private List<Shape> shapes;
    private List<Shape> selectedShapes;
    private Repository repository;
    private ActionCanvas delegateCanvas;
    private Stack<Shape> listShapes = new Stack<>();

    public Board() {
        this.shapes = new ArrayList<Shape>();
        this.selectedShapes = new ArrayList<Shape>();
    }

    public void setDelegateCanvas(ActionCanvas delegateCanvas) {
        this.delegateCanvas = delegateCanvas;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void saveData() throws Exception {
        if (repository == null) {
            throw new Exception("To save data need assign a repository");
        }
        repository.save(shapes);
    }

    public void loadData() throws Exception {
        if (repository == null) {
            throw new Exception("To load data need assign a repository");
        }
        shapes = repository.load();
        repaint();
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
            if (shape instanceof MainClass && shape.isLocated(point)) {
                return (MainClass) shape;
            }
        }
        return null;
    }

    public Shape getShape(Point point) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.isLocated(point)) {
                return shape;
            }
        }
        return null;
    }

    public void selectShape(Point point) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.isLocated(point)) {
                if (selectedShapes.contains(shape)) {
                    selectedShapes.remove(shape);
                } else {
                    selectedShapes.add(shape);
                }
            }
        }
    }

    public boolean isSelected(Shape shape) {
        return selectedShapes.contains(shape);
    }

    public void undo() {
        if (shapes.isEmpty()) {
            return;
        }
        Shape shape = shapes.get(shapes.size() - 1);
        listShapes.push(shapes.get(shapes.size() - 1));
        shapes.remove(shape);
        repaint();
    }

    public void redo(){
        if (listShapes.isEmpty()) {
            return;
        }
            shapes.add(listShapes.pop());
            repaint();
    }

    public void clean(){
        shapes.clear();
        repaint();
    }

    public void moveSelected(int x, int y) {
        for (Shape shape : selectedShapes) {
            if (shape instanceof MainClass) {
                MainClass mainClass = (MainClass) shape;
                mainClass.move(x, y);
            }
        }
    }

    private void repaint(){
        if (this.delegateCanvas != null) {
            this.delegateCanvas.repaintCanvas();
        }
    }
}