package com.houvet.UI.Panel;

import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;

public abstract class FormPanel extends JPanel {

    protected MainWindow mainWindow;

    /**
     *
     * @param main window caller
     */
    public FormPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.init();
    }

    /**
     * default constructor
     */
    public FormPanel() {}

    /**
     * init content of the panel
     */
    protected void init() {
        initSouthPanel();
    }

    /**
     * Add panel for button Add and Back
     * @return the panel
     */
    protected JPanel initSouthPanel() {
        JPanel southPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton add = new JButton("Add");

        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(back);
        southPanel.add(add);
        back.addActionListener(e -> this.backAction());
        add.addActionListener(e -> this.addAction());
        return(southPanel);
    }

    /**
     * to implement action of Back button
     */
    abstract void backAction();

    /**
     * to implement action of Add button
     */
    abstract void addAction();
}
