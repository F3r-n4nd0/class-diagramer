package UI.ToolBar;

import Domain.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ToolBar extends JMenuBar {

    private Board board;

    public ToolBar(Board board) {
        this.board = board;

        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        add(menu);

        menuItem = new JMenuItem("New");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem);


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

        menu.add(new JSeparator());

        menuItem = new JMenuItem("Exit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                System.exit(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // option menu
        menu = new JMenu("Edit");
        add(menu);

        menuItem = new JMenuItem("Undo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                board.undo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                //board.redo();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // option menu
        menu = new JMenu("Option");
        add(menu);

        menuItem = new JMenuItem("Normal Class");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Interface Class");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Abstract Class");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Association");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Composition");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Direct Association");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        menuItem = new JMenuItem("Inherit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        menu.add(menuItem);

        // horizontal space
        add(Box.createHorizontalGlue());

        // Help menu
        menu = new JMenu("Help");
        add(menu);

        menuItem = new JMenuItem("About");
        menu.add(menuItem);
//        menuItem.addActionListener(new FormMain.NewFileListener());
    }
}
