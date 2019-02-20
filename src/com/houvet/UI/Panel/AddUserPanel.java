package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.User;
import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddUserPanel extends FormPanel {

    private Group group;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField phone;
    private JTextField mail;

    /**
     *
     * @param main window caller
     * @param group to add the user
     */
    public AddUserPanel(MainWindow main, Group group) {
        super(main);
        this.group = group;
    }

    /**
     * Create frame content
     * Add fields to create a user
     */
    @Override
    protected void init()  {
        this.firstName = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name");
        this.lastName = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name");
        this.phone = new JTextField();
        JLabel phoneLabel = new JLabel("Phone");
        this.mail = new JTextField();
        JLabel mailLabel = new JLabel("Mail");
        JPanel fieldsPanel = new JPanel();

        firstName.setPreferredSize(new Dimension(400, 30));
        lastName.setPreferredSize(new Dimension(400, 30));
        phone.setPreferredSize(new Dimension(400, 30));
        mail.setPreferredSize(new Dimension(400, 30));
        fieldsPanel.add(firstName);
        fieldsPanel.add(firstNameLabel);
        fieldsPanel.add(lastName);
        fieldsPanel.add(lastNameLabel);
        fieldsPanel.add(phone);
        fieldsPanel.add(phoneLabel);
        fieldsPanel.add(mail);
        fieldsPanel.add(mailLabel);
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
     * On click to add button, user is created and you return to userGroup panel
     */
    void addAction() {
        try {
            User user = new User(firstName.getText(), lastName.getText(), phone.getText(), mail.getText());
            this.mainWindow.getDashboard().getGroupLists().addUser(user, this.group);
            this.mainWindow.changePanel("usersGroup");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
