package com.houvet.UI.Panel;

import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private MainWindow mainWindow;

    /**
     * @param main window caller
     */
    public MainPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.init();
    }

    /**
     * create application MainPanel
     * add navigation button
     */
    private void init() {
        JPanel buttonsPanel = new JPanel();
        JButton userGroup = new JButton("User Group");
        JButton projects = new JButton("Projects");
        JPanel dashboard = new JPanel();
        JLabel title = new JLabel("Task Manager Dashboard");

        userGroup.addActionListener(e -> this.mainWindow.changePanel("usersGroup"));
        projects.addActionListener(e -> this.mainWindow.changePanel("projects"));
        for (String event : this.mainWindow.getDashboard().getEventList()) {
            JLabel eventLabel = new JLabel(event, new ImageIcon("images/notif.png"), JLabel.LEFT);
            eventLabel.setPreferredSize(new Dimension(500, 30));
            dashboard.add(eventLabel);
        }
        title.setHorizontalAlignment(SwingConstants.CENTER);
        buttonsPanel.add(userGroup);
        buttonsPanel.add(projects);
        this.add(dashboard, BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }
}
