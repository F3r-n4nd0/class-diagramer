package UI.ToolBar;

import Domain.Board.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JMenuBar {

    private Board board;

    public ToolBar(Board board) {
        this.board = board;


        JMenu menu;
        JMenuItem mi;

        menu = new JMenu("File");
        add(menu);

        mi = new JMenuItem("New");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        mi = new JMenuItem("Open");
        menu.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    board.loadData();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        mi = new JMenuItem("save");
        menu.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    board.saveData();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        mi = new JMenuItem("save As");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        menu.add(new JSeparator());

//        exitAction = new FormMain.ExitListener();
        mi = new JMenuItem("Exit");
        menu.add(mi);
//        mi.addActionListener(exitAction);

        // option menu
        menu = new JMenu("Option");
        add(menu);

        mi = new JMenuItem("Color");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        // horizontal space
        add(Box.createHorizontalGlue());

        // Help menu
        menu = new JMenu("Help");
        add(menu);

        mi = new JMenuItem("About");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());
    }
}
