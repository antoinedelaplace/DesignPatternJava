package com.houvet;

import com.houvet.Pattern.Observable;
import com.houvet.Pattern.Observer;
import com.houvet.UI.Window.MainWindow;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by benoit-xavierhouvet on 01/03/2017.
 */
public class Scanner extends Thread implements Observable{

    private String PathProject = "data/projects/";
    private Dashboard dashboard;
    private ArrayList<Observer> observers = new ArrayList<>();
    private ArrayList<String> events = new ArrayList<>();

    public ArrayList<String> listDirectory() {
        ArrayList<String> directory = new ArrayList<String>();
        File file = new File(this.PathProject);
        File[] files = file.listFiles();
        if (files != null)
        {
            for (File fil : files) {
                if (fil.isDirectory())
                    directory.add(fil.getName());
            }
        }
        return directory;
    }

    public Scanner(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Scanner() {
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Project> merged = mergeProject(dashboard.getProjectList().getProjects());
            //System.out.println(merged);
            //System.out.println(events);
            //System.out.println(dashboard.getProjectList());
            if (!(merged.equals(dashboard.getProjectList()))) {
                dashboard.setProjectList(new ProjectList(merged));
                this.notifyObservers(events);
                //dashboard.setProjectList(new ProjectList(mergeProject(dashboard.getProjectList().getProjects())));
            }
        }
    }

    public ArrayList<ProjectFile> listFilesDirectory(Project project) {
        ArrayList<ProjectFile> filesdirectory = new ArrayList<ProjectFile>();
        File file = new File(this.PathProject.concat(project.getName()));
        File[] files = file.listFiles();
        if (files != null) {
            for (File fil : files) {
                if (fil.isFile())
                    filesdirectory.add(new ProjectFile(fil.getName()));
            }
        }
        return filesdirectory;
    }

    public ArrayList<Project> mergeProject(ArrayList<Project> actual)
    {
        ArrayList<Project> json = new ArrayList<Project>();
        for (Project projet : actual)
        {
            ArrayList<ProjectFile> actualFile = new ArrayList<>();
            for (ProjectFile singleFile : projet.getFiles()) {
                actualFile.add(new ProjectFile(singleFile.getName()));
            }
            json.add(new Project(projet.getName(), projet.getDescription(), projet.getState(), projet.getGroups(), projet.getUsers(), actualFile));
        }
        if (events != null) {
            events = new ArrayList<>();
        }
        ArrayList<Project> resultat = new ArrayList<>();
        ArrayList<String> directory = listDirectory();
        for (String dir : directory) {
            //System.out.println(dir);
            //System.out.println(json);
            boolean flag = false;
            if (json != null) {
                for (Project prj : json) {
                    if (prj.getName().equals(dir)) {
                        ArrayList<ProjectFile> files = listFilesDirectory(prj);
                        ArrayList<ProjectFile> newfile = new ArrayList<>();
                        for (ProjectFile file2 : files) {
                            boolean flag2 = false;
                            for (ProjectFile file1 : prj.getFiles()) {
                                if (file1.getName().equals(file2.getName())) {
                                    newfile.add(new ProjectFile(file1.getName()));
                                    file1.setName("null");
                                    flag2 = true;
                                }
                            }
                            if (!flag2) {
                                events.add("Le fichier "+file2.getName()+" a été créé dans le projet "+prj.getName());

                                newfile.add(new ProjectFile(file2.getName()));
                            }
                        }
                        for (ProjectFile file : prj.getFiles()) {
                            if (!(file.getName().equals("null")))
                                events.add("Le fichier "+file.getName()+" a été supprimé dans le projet "+prj.getName());
                        }

                        prj.setFiles(newfile);
                        resultat.add((new Project(prj.getName(), prj.getDescription(), prj.getState(), prj.getGroups(), prj.getUsers(), newfile)));
                        prj.setName("null");
                        flag = true;
                    }
                }
            }
            if (!flag || json == null) {
                events.add("Le projet "+dir+" a été créé");
                resultat.add(new Project(dir));
            }
        }
        for (Project prj : json) {
            if (!(prj.getName().equals("null")))
                events.add("Le projet "+prj.getName()+" a été supprimé");
        }
        ArrayList<Project> result = new ArrayList<>();
        for (Project project : resultat) {
            result.add(new Project(project.getName(), project.getDescription(), project.getState(), project.getGroups(), project.getUsers(), project.getFiles()));
        }
        return result;
    }


    public void deleteFolder(String dir) {
        File file = new File(this.PathProject.concat(dir));
        String[]entries = file.list();
        if (entries != null) {
            for (String s : entries) {
                File currentFile = new File(file.getPath(), s);
                currentFile.delete();
            }
        }
        file.delete();
    }

    public boolean addFolder(String dir) {
        File file = new File(this.PathProject.concat(dir));
        if (!file.exists()) {
            return (file.mkdir());
        }
        return false;
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
