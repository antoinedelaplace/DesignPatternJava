package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.Project;
import com.houvet.UI.Window.MainWindow;
import com.houvet.User;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ProjectGroupPanel extends JPanel {
    private MainWindow mainWindow;
    private Project project;

    /**
     * @param main window caller
     */
    public ProjectGroupPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new GridLayout(1, 2));
        this.project = new Project();
        this.init(false);
    }

    /**
     * Create frame content
     * launch buildTree function to display group and user lists
     * Add panel for button Affect group/User to a project
     */
    void init(boolean visible) {
        JTree tree = buildTree();
        JPanel treePanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel();
        JButton affectGroup = new JButton("Affect a Group");
        JButton affectUser = new JButton("Affect a User");

        this.add(treePanel);
        treePanel.add(new JScrollPane(tree),BorderLayout.CENTER);
        treePanel.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(affectGroup);
        southPanel.add(affectUser);
        affectGroup.setVisible(visible);
        affectUser.setVisible(visible);
        affectGroup.addActionListener(e -> this.mainWindow.affectGroup(this.project));
        affectUser.addActionListener(e -> this.mainWindow.affectUser(this.project));
        TreeCellRenderer cellRenderer = tree.getCellRenderer();
        if (cellRenderer instanceof DefaultTreeCellRenderer) {
            DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)cellRenderer;
            renderer.setClosedIcon(new ImageIcon("images/group.png"));
            renderer.setOpenIcon(new ImageIcon("images/group.png"));
            renderer.setLeafIcon(new ImageIcon("images/user.jpg"));
        }
    }

    /**
     * @return tree with all groups and users
     */
    protected JTree buildTree() {
        DefaultMutableTreeNode userGroup = new DefaultMutableTreeNode("Project Group/User");
        ArrayList<Group> groups = this.project.getGroups();
        ArrayList<User> users =  this.project.getUsers();

        for (Group group : groups) {
            DefaultMutableTreeNode treeGroup = new DefaultMutableTreeNode(group);
            userGroup.add(treeGroup);
            for (User user : group.getUsers()) {
                treeGroup.add(new DefaultMutableTreeNode(user));
            }
        }
        for (User user : users) {
            DefaultMutableTreeNode treeGroup = new DefaultMutableTreeNode(user);
            userGroup.add(treeGroup);
        }
        return (new JTree(userGroup));
    }

    public void setProject(Project project) {
        this.project = project;
        resetPanel(true);
    }

    public void resetPanel(boolean visible) {
        this.removeAll();
        this.init(visible);
        this.revalidate();
        this.repaint();
    }
}
