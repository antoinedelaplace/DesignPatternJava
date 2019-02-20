package com.houvet.UI.Panel;

import com.houvet.Project;
import com.houvet.State;
import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddProjectPanel extends FormPanel {

    private JTextField projectName;
    private JTextArea description;
    private JComboBox states;

    /**
     * @param main window caller
     */
    public AddProjectPanel(MainWindow main) {
        super(main);
    }

    /**
     * Create frame content
     * Add fields to create a group
     * Add panel for button Add and Back
     * On click to add, group is created and you return to userGroup panel
     */
    @Override
    protected void init()  {
        this.projectName = new JTextField();
        JLabel groupNameLabel = new JLabel("Project Name");
        this.description = new JTextArea();
        JLabel descriptionLabel = new JLabel("Description");
        JPanel fieldsPanel = new JPanel();
        JLabel stateLabel = new JLabel("State");
        this.states = new JComboBox(State.values());

        projectName.setPreferredSize(new Dimension(500, 30));
        description.setPreferredSize(new Dimension(500, 500));
        fieldsPanel.add(groupNameLabel);
        fieldsPanel.add(projectName);
        fieldsPanel.add(descriptionLabel);
        fieldsPanel.add(description);
        fieldsPanel.add(stateLabel);
        fieldsPanel.add(states);
        this.setLayout(new BorderLayout());
        this.add(fieldsPanel,BorderLayout.CENTER);
        super.init();
    }

    /**
     * On click to back button, you return to userGroup panel
     */
    void backAction() {
        this.mainWindow.changePanel("projects");
    }

    /**
     * On click to add button, group is created and you return to userGroup panel
     */
    void addAction() {
        try {
            Project project = new Project(projectName.getText(), description.getText(), (State)states.getSelectedItem());
            this.mainWindow.getDashboard().getProjectList().addProject(project);
            this.mainWindow.changePanel("projects");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
