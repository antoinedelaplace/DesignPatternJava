package com.houvet.UI.Panel;

import com.houvet.Project;
import com.houvet.UI.Window.MainWindow;
import com.houvet.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AffectUserPanel extends FormPanel {

    private Project project;
    private JComboBox users;

    /**
     * @param main window caller
     * @param project affect the user to this project
     */
    public AffectUserPanel(MainWindow main, Project project) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.project = project;
        this.init();
    }

    /**
     * Create frame content
     * Add fields to select a user
     */
    @Override
    protected void init()  {
        JLabel projectName = new JLabel(this.project.getName());
        JLabel projectLabel = new JLabel("Project");
        JPanel fieldsPanel = new JPanel();
        this.users = new JComboBox();

        for (User user : this.mainWindow.getDashboard().getGroupLists().getUsers()) {
            users.addItem(user);
        }
        fieldsPanel.add(projectLabel);
        fieldsPanel.add(projectName);
        fieldsPanel.add(users);
        this.add(fieldsPanel,BorderLayout.CENTER);
        super.init();
    }

    /**
     * On click to back button, you return to projects panel
     */
    void backAction() {
        this.mainWindow.changePanel("projects");
    }

    /**
     * On click to add button, user is affected to project and you return to project panel
     */
    void addAction() {
        try {
            this.mainWindow.getDashboard().getProjectList().addUser(this.project, (User)users.getSelectedItem());
            this.mainWindow.changePanel("projects");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
