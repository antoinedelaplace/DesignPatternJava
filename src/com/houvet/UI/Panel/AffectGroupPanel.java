package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.Project;
import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AffectGroupPanel extends FormPanel {

    private Project project;
    private JComboBox groups;

    /**
     * @param main window caller
     * @param project affect the group to this project
     */
    public AffectGroupPanel(MainWindow main, Project project) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.project = project;
        this.init();
    }

    /**
     * Create frame content
     * Add fields to select a group
     */
    @Override
    protected void init()  {
        JLabel projectName = new JLabel(this.project.getName());
        JLabel projectLabel = new JLabel("Project");
        JPanel fieldsPanel = new JPanel();
        this.groups = new JComboBox();

        for (Group group : this.mainWindow.getDashboard().getGroupLists().getGroups()) {
            groups.addItem(group);
        }
        fieldsPanel.add(projectLabel);
        fieldsPanel.add(projectName);
        fieldsPanel.add(groups);
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
     * On click to add button, group is affected to project and you return to project panel
     */
    void addAction() {
        try {
            this.mainWindow.getDashboard().getProjectList().addGroup(this.project, (Group)groups.getSelectedItem());
            this.mainWindow.changePanel("projects");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
