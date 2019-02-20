package com.houvet;

import com.houvet.Pattern.Observable;
import com.houvet.Pattern.Observer;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by benoit-xavierhouvet on 28/02/2017.
 */
public class ProjectList implements Serializable, Observable {
    private ArrayList<Project> projects = new ArrayList<>();
    private String PathProjects = "data/json/project.json";
    private ArrayList<Observer> observers = new ArrayList<>();



    public boolean addProject(Project project) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName()))
                return false;
        }
        projects.add(project);
        this.notifyObservers("Le projet "+project.getName()+" a été créé");
        Scanner folder = new Scanner();
        folder.addFolder(project.getName());
        saveProject();
        return true;
    }

    public boolean removeProject(Project project) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                projects.remove(project);
                this.notifyObservers("Le projet "+project.getName()+" a été supprimé");
                Scanner folder = new Scanner();
                folder.deleteFolder(project.getName());
                saveProject();
                return true;
            }
        }
        return false;
    }

    public boolean addUser(Project project, User user) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.addUser(user))
                    return false;
                this.notifyObservers("Le user "+user.getName()+ " a été affecté au projet " +project.getName());
            }
        }
        saveProject();
        return true;
    }

    public boolean removeUser(Project project, User user) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.removeUser(user))
                    return false;
            }
        }
        saveProject();
        return true;
    }

    public boolean addFile(Project project, ProjectFile file) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.addFile(file))
                    return false;
            }
        }
        saveProject();
        return true;
    }

    public boolean removeFile(Project project, ProjectFile file) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.removeFile(file))
                    return false;
            }
        }
        saveProject();
        return true;
    }

    public boolean addGroup(Project project, Group group) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.addGroup(group))
                    return false;
                this.notifyObservers("Le groupe "+group.getName()+ " a été affecté au projet " +project.getName());
            }
        }
        saveProject();
        return true;
    }

    public boolean removeGroup(Project project, Group group) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                if (!prj.removeGroup(group))
                    return false;
            }
        }
        saveProject();
        return true;
    }

    public boolean removeGroup(Group group) throws IOException {
        for (Project prj : projects)
            prj.removeGroup(group);
        saveProject();
        return true;
    }

    public boolean updateState(Project project, State state) throws IOException {
        for (Project prj : projects) {
            if (prj.getName().equals(project.getName())) {
                prj.setState(state);
            }
        }
        saveProject();
        return true;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) throws IOException {
        this.projects = projects;
        saveProject();
    }

    public ProjectList() throws IOException {
        loadProject();
        System.out.println(getProjects());
        Scanner scan = new Scanner();
        setProjects(scan.mergeProject(getProjects()));
        System.out.println(getProjects());
        saveProject();
        //System.out.println(getProjects());
    }

    public ProjectList(ArrayList<Project> projects) {
        this.projects = projects;
        this.PathProjects = "data/json/project.json";
    }

    public void saveProject() throws IOException {
        try {
            File file = new File(this.PathProjects);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fichier = new FileOutputStream(this.PathProjects);
            ObjectOutputStream project = new ObjectOutputStream(fichier);
            project.writeObject(getProjects());

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void loadProject() throws IOException {
        try {
            FileInputStream file = new FileInputStream(this.PathProjects);
            ObjectInputStream project = new ObjectInputStream(file);
            ArrayList<Project> projects = (ArrayList<Project>) project.readObject();
            System.out.println(projects);
            setProjects(projects);
            //return projects;
        }
        catch (Exception ex) {
          //  return null;
        }
    }

    @Override
    public String toString() {
        return "ProjectList{" +
                "projects=" + projects +
                ", PathProjects='" + PathProjects + '\'' +
                '}';
    }

    @Override
    public void addObserver(Observer observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(Observer observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.receiveNotification(this, event);
        }
    }

    @Override
    public void notifyObservers(ArrayList<String> events) {
        for (Observer observer : observers) {
            for (String event : events)
                observer.receiveNotification(this, event);
        }
    }
}
