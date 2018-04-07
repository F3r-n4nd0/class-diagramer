package UI;

import Domain.Board.Board;
import Domain.Shape.Classes.AbstractClass;
import Domain.Shape.Classes.InterfaceClass;
import Domain.Shape.Classes.NormalClass;
import Domain.Shape.Connectors.Association;
import Domain.Shape.Connectors.Composition;
import Domain.Shape.Connectors.DirectAssociation;
import Domain.Shape.Connectors.Inherit;
import Domain.Shape.Shape;
import UI.Canvas.Canvas;
import UI.MenuBar.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormMain extends JFrame {

    protected static int width = 700;
    protected static int height = 500;

    private Board board;
    private UI.Canvas.Canvas canvas;
    private ToolBar toolBar;
    protected MenuBar menuBar;

    public FormMain() throws Exception {
        super("Class Diagrammer");
        instanceControls();
        addControls();
    }

    private void instanceControls() throws Exception {
        board = new Board();
        menuBar = new MenuBar(getShapesMenu());
        canvas = new Canvas(board, menuBar);
        toolBar = new ToolBar();

    }

    private void addControls() throws Exception {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(menuBar, BorderLayout.WEST);

    }

    public void showFrame() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public List<Shape> getShapesMenu() throws Exception {
        List<Shape> shapesMenu = new ArrayList<Shape>();
        shapesMenu.add(new NormalClass("Normal Class"));
        shapesMenu.add(new InterfaceClass("Interface Class"));
        shapesMenu.add(new AbstractClass("Abstract Class"));
        shapesMenu.add(new Association());
        shapesMenu.add(new DirectAssociation());
        shapesMenu.add(new Composition());
        shapesMenu.add(new Inherit());
        return shapesMenu;
    }

}
