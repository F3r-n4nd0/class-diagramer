package UI.MenuBar;

import Domain.Shape.Shape;
import UI.Canvas.MenuShapesDelegate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MenuBar extends JPanel implements MenuSelectShapeDelegate, MenuShapesDelegate {

    public static final int WIDTH_MENU = 150;

    private MenuShapeButtonListener menuButtonListener;
    private List<ShapeButton> shapesMenuButtons;
    private ShapeButton buttonSelected;

    public MenuBar(List<Shape> shapes) throws Exception {
        super(new GridLayout(0, 1));
        setPreferredSize(new Dimension(WIDTH_MENU, 200));

        this.menuButtonListener = new MenuShapeButtonListener(this);
        this.shapesMenuButtons = convertShapesTosButtons(shapes);

        loadButtonsToMenu();
    }

    private List<ShapeButton> convertShapesTosButtons(List<Shape> shapes) {
        List<ShapeButton> buttons = new ArrayList<ShapeButton>();
        for (Shape shape : shapes) {
            ShapeButton button = new ShapeButton(shape);
            button.addActionListener(menuButtonListener);
            buttons.add(button);
        }
        return buttons;
    }

    private void loadButtonsToMenu() {
        for (ShapeButton button : shapesMenuButtons) {
            add(button);
        }
    }

    @Override
    public void menuSelect(ShapeButton buttonShape) {
        if (buttonSelected != null) {
            buttonSelected.isSelected = false;
            buttonSelected.repaint();
        }
        buttonSelected = buttonShape;
        buttonSelected.isSelected = true;
        buttonSelected.repaint();
    }

    @Override
    public Shape getSelectShape() {
        if (buttonSelected == null) {
            return null;
        }
        return buttonSelected.getShape();
    }

    @Override
    public void deselectAll() {
        buttonSelected.isSelected = false;
        buttonSelected.repaint();
        buttonSelected = null;
    }

    public void Select(Class<?> mainClass) {
        if (buttonSelected != null) {
            buttonSelected.isSelected = false;
            buttonSelected.repaint();
        }
        ShapeButton buttonShape = findShapeButton(mainClass);
        if (buttonShape != null) {
            buttonSelected = buttonShape;
            buttonSelected.isSelected = true;
            buttonSelected.repaint();
        }
    }

    private ShapeButton findShapeButton(Class<?> shapeClass) {
        for (ShapeButton shapeButton : shapesMenuButtons) {
            if (shapeClass.isInstance(shapeButton.getShape())) {
                return shapeButton;
            }
        }
        return null;
    }

}
