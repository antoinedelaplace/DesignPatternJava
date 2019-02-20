package com.houvet;

import java.io.Serializable;
import java.util.ArrayList;

public class Project extends Commentable implements Serializable {
    private String name;
    private String description;
    private State state;
    private ArrayList<Group> groups;
    private ArrayList<User> users;
    private ArrayList<ProjectFile> files;

    /**
     * create new com.houvet.Project
     * @param name
     * @param description
     * @param state
     */
    public Project(String name, String description, State state) {

        this.name = name;
        this.description = description;
        this.state = state;
        this.groups = new ArrayList<Group>();
        this.users = new ArrayList<User>();
        this.files = new ArrayList<ProjectFile>();
    }

    public Project(String name, String description, State state, ArrayList<Group> groups, ArrayList<User> users, ArrayList<ProjectFile> projectFiles) {

        this.name = name;
        this.description = description;
        this.state = state;
        this.groups = groups;
        this.users = users;
        this.files = projectFiles;
    }

    /**
     * create empty Project (in case of ProjectGroupPanel)
     */
    public Project() {
        this.groups = new ArrayList<Group>();
        this.users = new ArrayList<User>();
        this.files = new ArrayList<ProjectFile>();
    }

    public Project(String name) {
        this.name = name;
        this.groups = new ArrayList<Group>();
        this.users = new ArrayList<User>();
        this.files = new ArrayList<ProjectFile>();
    }

    public boolean addGroup(Group group) {
        for (Group grp : groups) {
            if (grp.getName().equals(group.getName()))
                return false;
        }
        groups.add(group);
        return true;
    }

    public boolean removeGroup(Group group) {
        for (Group grp : groups) {
            if (grp.getName().equals(group.getName())) {
                groups.remove(grp);
                return true;
            }
        }
        return false;
    }

    public boolean addUser(User user) {
        for (User usr : users) {
            if (usr.equals(user))
                return false;
        }
        users.add(user);
        return true;
    }

    public boolean removeUser(User user) {
        for (User usr : users) {
            if (usr.equals(user)) {
                users.remove(usr);
                return true;
            }
        }
        return false;
    }

    public boolean addFile(ProjectFile file) {
        for (ProjectFile fil : files) {
            if (fil.equals(file))
                return false;
        }
        files.add(file);
        return true;
    }

    public boolean removeFile(ProjectFile file) {
        for (ProjectFile fil : files) {
            if (fil.equals(file)) {
                files.remove(fil);
                return true;
            }
        }
        return false;
    }

    /**
     * @return project name
     */
    public String getName() { return name; }

    /**
     * change project name
     * @param name new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * @return project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * change project description
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return project state
     */
    public State getState() {
        return state;
    }

    /**
     * change project state
     * @param state new state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return project List groups
     */
    public ArrayList<Group> getGroups() { return groups; }

    /**
     * change allowed groups to project
     * @param groups
     */
    public void setGroups(ArrayList<Group> groups) { this.groups = groups; }

    /**
     * @return project list users
     */
    public ArrayList<User> getUsers() { return users; }

    /**
     * change allowed users to project
     * @param users
     */
    public void setUsers(ArrayList<User> users) { this.users = users; }

    /**
     * @return List of file
     */
    public ArrayList<ProjectFile> getFiles() { return files; }

    /**
     * change file on project
     * @param files
     */
    public void setFiles(ArrayList<ProjectFile> files) { this.files = files; }

    @Override
    public String toString() {
        if (this.state == null)
            return (this.name);
        else
            return (this.name + " (" + this.state + ")");
    }

}
