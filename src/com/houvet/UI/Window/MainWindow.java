package com.houvet.UI.Window;

import com.houvet.*;
import com.houvet.Pattern.Observable;
import com.houvet.Pattern.Observer;
import com.houvet.UI.Panel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainWindow extends Window implements Observer {

    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private Dashboard dashboard;
    private String actualPanel;

    /**
     * init main UI and different Panel/Layout
     * @param title
     * @param width
     * @param height
     */
    public MainWindow(String title, int width, int height, Dashboard dashboard) {
        super(title, width, height);
        this.actualPanel = "main";
        this.dashboard = dashboard;
        this.dashboard.getScanner().addObserver(this);
        JPanel guiPanel = new JPanel(new BorderLayout());
        this.cardLayout = new CardLayout();
        this.cardsPanel = new JPanel(this.cardLayout);
        guiPanel.add(cardsPanel);
        this.initPanels();
        this.getContentPane().add(guiPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * init applications panels
     * add them into cardLayout
     */
    private void initPanels() {
        this.cardsPanel.add(new MainPanel(this), "main");
        this.cardsPanel.add(new ProjectPanel(this), "projects");
        this.cardsPanel.add(new UserGroupPanel(this), "usersGroup");
    }

    /**
     * @return application dashboard
     */
    public Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * @param labelPanel show this panel and hide others
     */
    public void changePanel(String labelPanel) {
        this.cardsPanel.removeAll();
        this.initPanels();
        this.cardsPanel.revalidate();
        this.cardsPanel.repaint();
        cardLayout.show(this.cardsPanel,labelPanel);
        this.actualPanel = labelPanel;
    }

    /**
     * Open the panel to add a new user
     * @param group to add in this group
     */
    public void addUser(Group group) {
        this.cardsPanel.add(new AddUserPanel(this, group), "addUser");
        cardLayout.show(this.cardsPanel, "addUser");
        this.actualPanel = "addUser";
    }

    /**
     * Open the panel to assign a user in a selected group
     * @param user to assign
     */
    public void assignUser(User user) {
        this.cardsPanel.add(new AssignUserPanel(this, user), "assignUser");
        cardLayout.show(this.cardsPanel, "assignUser");
        this.actualPanel = "assignUser";
    }

    /**
     * Open the panel to add a new group
     */
    public void addGroup() {
        this.cardsPanel.add(new AddGroupPanel(this), "addGroup");
        cardLayout.show(this.cardsPanel, "addGroup");
        this.actualPanel = "addGroup";
    }

    /**
     * Open the panel to add a new project
     */
    public void addProject() {
        this.cardsPanel.add(new AddProjectPanel(this), "addProject");
        cardLayout.show(this.cardsPanel, "addProject");
        this.actualPanel = "addProject";
    }

    /**
     * Open the panel to affect a group to a project
     */
    public void affectGroup(Project project) {
        this.cardsPanel.add(new AffectGroupPanel(this, project), "affectGroup");
        cardLayout.show(this.cardsPanel, "affectGroup");
        this.actualPanel = "affectGroup";
    }

    /**
     * Open the panel to affect a user to a project
     */
    public void affectUser(Project project) {
        this.cardsPanel.add(new AffectUserPanel(this, project), "affectUser");
        cardLayout.show(this.cardsPanel, "affectUser");
        this.actualPanel = "affectUser";
    }

    /**
     * Open the panel to affect a user to a project
     */
    public void addMessage(Commentable commentable) {
        this.cardsPanel.add(new AddMessagePanel(this, this.actualPanel ,commentable), "addMessage");
        cardLayout.show(this.cardsPanel, "addMessage");
        this.actualPanel = "addMessage";
    }

    public void reloadPanel() {
        if (actualPanel.equals("projects"))
            this.changePanel("projects");
        else if (actualPanel.equals("main"))
            this.changePanel("main");
    }

    @Override
    public void receiveNotification(Observable observable, ArrayList<String> event) {
        reloadPanel();
    }

    @Override
    public void receiveNotification(Observable observable, String event) {
        reloadPanel();
    }
}
