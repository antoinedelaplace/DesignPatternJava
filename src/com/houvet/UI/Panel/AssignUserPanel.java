package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.UI.Window.MainWindow;
import com.houvet.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AssignUserPanel extends FormPanel {

    private User user;
    private JComboBox groups;

    /**
     * @param main window caller
     * @param user to add the user in a group
     */
    public AssignUserPanel(MainWindow main, User user) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.user = user;
        this.init();
    }

    /**
     * Create frame content
     * Add fields to select a group
     */
    @Override
    protected void init()  {
        JLabel userName = new JLabel(this.user.getName());
        JLabel userLabel = new JLabel("User");
        JPanel fieldsPanel = new JPanel();
        this.groups = new JComboBox();

        for (Group group : this.mainWindow.getDashboard().getGroupLists().getGroups()) {
            groups.addItem(group);
        }
        fieldsPanel.add(userLabel);
        fieldsPanel.add(userName);
        fieldsPanel.add(groups);
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
     * On click to add button, user is assigned to group and you return to userGroup panel
     */
    void addAction() {
        try {
            this.mainWindow.getDashboard().getGroupLists().addUserToGroup(user, (Group)groups.getSelectedItem());
            this.mainWindow.changePanel("usersGroup");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
