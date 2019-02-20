package com.houvet.UI.Panel;

import com.houvet.Project;
import com.houvet.ProjectFile;
import com.houvet.UI.Window.MainWindow;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectPanel extends JPanel {
    private MainWindow mainWindow;

    /**
     * @param main window caller
     */
    public ProjectPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new GridLayout(1, 2));
        this.init();
    }

    /**
     * Create frame content
     * launch buildTree to display project list
     * Add panel for button Add, Delete and Back
     */
    void init() {
        JTree tree = buildTree();
        JPanel treePanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel();
        JButton back = new JButton("Back");
        JButton add = new JButton("Add");
        JButton del = new JButton("Delete");
        JPanel infos = new JPanel(new GridLayout(2, 1));
        MessagesPanel messagesPanel = new MessagesPanel(mainWindow);
        ProjectGroupPanel projectGroupPanel = new ProjectGroupPanel(this.mainWindow);

        this.add(treePanel);
        this.add(infos);
        infos.add(projectGroupPanel);
        infos.add(messagesPanel);
        treePanel.add(new JScrollPane(tree),BorderLayout.CENTER);
        treePanel.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(back);
        southPanel.add(add);
        southPanel.add(del);
        back.addActionListener(e -> this.mainWindow.changePanel("main"));
        add.addActionListener(e -> this.mainWindow.addProject());
        del.setVisible(false);
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(treeNode.getUserObject().getClass() == String.class) {
                for (ActionListener al : add.getActionListeners())
                    add.removeActionListener(al);
                add.addActionListener(event -> mainWindow.addProject());
                del.setVisible(false);
                add.setVisible(true);
                projectGroupPanel.resetPanel(false);
                messagesPanel.hideMessages();
                messagesPanel.hideAddButton();
            }

            else if (treeNode.getUserObject().getClass() == Project.class) {
                for (ActionListener al : add.getActionListeners())
                    add.removeActionListener(al);
                add.addActionListener(event -> {
                    //TODO add a project file to the project
                });
                add.setVisible(false);
                del.addActionListener(event -> {
                    try {
                        mainWindow.getDashboard().getProjectList().removeProject((Project) treeNode.getUserObject());
                        mainWindow.changePanel("projects");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                del.setVisible(true);
                projectGroupPanel.setProject((Project) treeNode.getUserObject());
                messagesPanel.displayMessages((Project) treeNode.getUserObject());
                messagesPanel.displayAddButton();
            }
            else if (treeNode.getUserObject().getClass() == ProjectFile.class) {
                del.addActionListener(event -> {
                    DefaultMutableTreeNode treeParent = (DefaultMutableTreeNode) treeNode.getParent();
                    try {
                        mainWindow.getDashboard().getProjectList().removeFile((Project) treeParent.getUserObject(), (ProjectFile) treeNode.getUserObject());
                        mainWindow.changePanel("projects");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                del.setVisible(true);
                add.setVisible(false);
                projectGroupPanel.resetPanel(false);
                messagesPanel.displayMessages((ProjectFile)treeNode.getUserObject());
                messagesPanel.displayAddButton();
            }
        });
    }

    /**
     * @return tree with all projects
     */
    protected JTree buildTree() {
        DefaultMutableTreeNode projectsList = new DefaultMutableTreeNode("Projects");
        ArrayList<Project> projects =  this.mainWindow.getDashboard().getProjectList().getProjects();

        for(Project project : projects) {
            DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project);
            projectsList.add(projectNode);
            for(ProjectFile file : project.getFiles()) {
                projectNode.add(new DefaultMutableTreeNode(file));
            }        }
        return (new JTree(projectsList));
    }
}
