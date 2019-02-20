package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.UI.Window.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddGroupPanel extends FormPanel {

    private JTextField groupName;
    private JTextArea description;

    /**
     * @param main window caller
     */
    public AddGroupPanel(MainWindow main) {
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
        this.groupName = new JTextField();
        JLabel groupNameLabel = new JLabel("Group Name");
        this.description = new JTextArea();
        JLabel descriptionLabel = new JLabel("Description");
        JPanel fieldsPanel = new JPanel();

        groupName.setPreferredSize(new Dimension(500, 30));
        description.setPreferredSize(new Dimension(500, 500));
        fieldsPanel.add(groupNameLabel);
        fieldsPanel.add(groupName);
        fieldsPanel.add(descriptionLabel);
        fieldsPanel.add(description);
        this.setLayout(new BorderLayout());
        this.add(fieldsPanel,BorderLayout.CENTER);
        super.init();
    }

    /**
     * On click to back button, you return to userGroup panel
     */
    void backAction() {
        this.mainWindow.changePanel("usersGroup");
    }

    /**
     * On click to add button, group is created and you return to userGroup panel
     */
    void addAction() {
        try {
            Group group = new Group(groupName.getText(), description.getText());
            this.mainWindow.getDashboard().getGroupLists().addGroup(group);
            this.mainWindow.changePanel("usersGroup");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
