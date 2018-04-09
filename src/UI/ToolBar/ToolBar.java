package UI.ToolBar;

import Domain.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {

    JMenu menu;
    JMenuItem menuItem;

    public ToolBar(Board board) {
        menu = new JMenu("File");
        add(menu);

        addMenuItem(menu, e -> board.clean(), "New", 'N', true);

        menuItem = new JMenuItem("Open");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                board.loadData();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        menuItem = new JMenuItem("Save");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                board.saveData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        menuItem = new JMenuItem("Save As");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                //board.saveData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        menu.add(new JSeparator());

        addMenuItem(menu, e -> System.exit(0), "Exit", 'E', true);

        // option menu
        menu = new JMenu("Edit");
        add(menu);

        addMenuItem(menu, e -> board.undo(), "Undo", 'U', true);
        addMenuItem(menu, e -> board.redo(), "Redo", 'R', true);

        // option menu
        menu = new JMenu("Option");
        add(menu);

        addMenuItem(menu, e -> board.clean(), "Normal Class", KeyEvent.VK_N, false);
        addMenuItem(menu, e -> board.clean(), "Interface Class", KeyEvent.VK_I, false);
        addMenuItem(menu, e -> board.clean(), "Abstract Class", KeyEvent.VK_A, false);
        addMenuItem(menu, e -> board.clean(), "Association Connector", KeyEvent.VK_S, false);
        addMenuItem(menu, e -> board.clean(), "Composition Connector", KeyEvent.VK_C, false);
        addMenuItem(menu, e -> board.clean(), "Direct Association Connector", KeyEvent.VK_D, false);
        addMenuItem(menu, e -> board.clean(), "Inherit Connector", KeyEvent.VK_H, false);

        // horizontal space
        add(Box.createHorizontalGlue());

        // Help menu
        menu = new JMenu("Help");
        add(menu);

        menuItem = new JMenuItem("About");
        menu.add(menuItem);
        //addMenuItem(menu, e -> openAbout, "Inherit Connector", 'H', false);

    }

    private void addMenuItem(JMenu menu, ToolBarListener listener, String name, int key, boolean type) {
        try {
            menuItem = new JMenuItem(name);
            if (type) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, ActionEvent.ALT_MASK));
            }
            menu.add(menuItem);
            menuItem.addActionListener(listener::exec);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}