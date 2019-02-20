package com.houvet.UI.Panel;

import com.houvet.Group;
import com.houvet.UI.Window.MainWindow;
import com.houvet.User;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class UserGroupPanel extends JPanel {
    private MainWindow mainWindow;

    /**
     * @param main window caller
     */
    public UserGroupPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new GridLayout(1, 2));
        this.init();
    }

    /**
     * Create frame content
     * launch buildTree function to display group and user lists
     * Add panel for button Add, Delete, Assign and Back
     */
    void init() {
        JTree tree = buildTree();
        JPanel treePanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton add = new JButton("Add");
        JButton del = new JButton("Delete");
        JButton assign = new JButton("Assign");
        MessagesPanel messagesPanel = new MessagesPanel(mainWindow);

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(treeNode.getUserObject().getClass() == String.class) {
                for (ActionListener al : add.getActionListeners())
                    add.removeActionListener(al);
                add.addActionListener(event -> mainWindow.addGroup());
                del.setVisible(false);
                add.setVisible(true);
                assign.setVisible(false);
                messagesPanel.hideMessages();
                messagesPanel.hideAddButton();
            }

            else if (treeNode.getUserObject().getClass() == Group.class) {
                for (ActionListener al : add.getActionListeners())
                    add.removeActionListener(al);
                add.addActionListener(event -> {
                    mainWindow.addUser((Group)treeNode.getUserObject());
                });
                add.setVisible(true);
                del.addActionListener(event -> {
                    try {
                        mainWindow.getDashboard().getGroupLists().removeGroup((Group) treeNode.getUserObject());
                        mainWindow.getDashboard().getProjectList().removeGroup((Group) treeNode.getUserObject());
                        mainWindow.changePanel("usersGroup");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                del.setVisible(true);
                assign.setVisible(false);
                messagesPanel.displayMessages((Group) treeNode.getUserObject());
                messagesPanel.displayAddButton();
            }
            else if (treeNode.getUserObject().getClass() == User.class) {
                del.addActionListener(event -> {
                    DefaultMutableTreeNode treeParent = (DefaultMutableTreeNode) treeNode.getParent();
                    try {
                        mainWindow.getDashboard().getGroupLists().removeUserFromGroup((User)treeNode.getUserObject(), (Group)treeParent.getUserObject());
                        mainWindow.changePanel("usersGroup");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                del.setVisible(true);
                add.setVisible(false);
                assign.setVisible(true);
                assign.addActionListener(event -> {
                    mainWindow.assignUser((User)treeNode.getUserObject());
                });
                messagesPanel.displayMessages((User)treeNode.getUserObject());
                messagesPanel.displayAddButton();
            }
        });
        TreeCellRenderer cellRenderer = tree.getCellRenderer();
        if (cellRenderer instanceof DefaultTreeCellRenderer) {
            DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)cellRenderer;
            renderer.setClosedIcon(new ImageIcon("images/group.png"));
            renderer.setOpenIcon(new ImageIcon("images/group.png"));
            renderer.setLeafIcon(new ImageIcon("images/user.jpg"));
        }
        this.add(treePanel);
        this.add(messagesPanel);
        treePanel.add(new JScrollPane(tree),BorderLayout.CENTER);
        treePanel.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(back);
        southPanel.add(add);
        southPanel.add(del);
        southPanel.add(assign);
        back.addActionListener(e -> this.mainWindow.changePanel("main"));
        add.addActionListener(e -> this.mainWindow.addGroup());
        del.setVisible(false);
        assign.setVisible(false);
    }

    /**
     * @return tree with all groups and users
     */
    private JTree buildTree() {
        DefaultMutableTreeNode userGroup = new DefaultMutableTreeNode("User Group");
        ArrayList<Group> groups =  this.mainWindow.getDashboard().getGroupLists().getGroups();

        for(Group group : groups) {
            DefaultMutableTreeNode treeGroup = new DefaultMutableTreeNode(group);
            userGroup.add(treeGroup);
            for(User user : group.getUsers()) {
                treeGroup.add(new DefaultMutableTreeNode(user));
            }
        }
        return (new JTree(userGroup));
    }
}
