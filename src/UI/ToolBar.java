package UI;

import javax.swing.*;

public class ToolBar extends JMenuBar {

    public ToolBar() {
        JMenu menu;
        JMenuItem mi;

        menu = new JMenu("File");
        add(menu);

        mi = new JMenuItem("New");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        mi = new JMenuItem("Open");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        mi = new JMenuItem("Save");
        menu.add(mi);
//        mi.addActionListener(new FormMain.NewFileListener());

        mi = new JMenuItem("Save As");
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
