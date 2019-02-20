package com.houvet.UI.Window;

import javax.swing.*;

abstract class Window extends JFrame {
    /**
     * construct a new visible UI
     * add close button to close the frame
     * @param title add a title
     * @param width choose a width
     * @param height choose a height
     */
    public Window(String title, int width, int height) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
