package com.houvet;

import com.houvet.Pattern.Observable;
import com.houvet.Pattern.Observer;

import java.io.IOException;
import java.util.ArrayList;

public class Dashboard implements Observer{
    private static Dashboard INSTANCE;
    private GroupList groupList;
    private ProjectList projectList;
    private ArrayList<String> eventList = new ArrayList<>();
    private Scanner scanner;

    public Scanner getScanner() {
        return scanner;
    }

    public void setProjectList(ProjectList projectList) {
        this.projectList = projectList;
    }

    public static synchronized Dashboard getInstance() throws IOException {
        if(INSTANCE == null)
            INSTANCE = new Dashboard();
        return (INSTANCE);
    }
    private Dashboard() throws IOException {
        this.groupList = new GroupList();
        this.projectList = new ProjectList();
        this.projectList.addObserver(this);
        this.groupList.addObserver(this);
        this.scanner = new Scanner(this);
        this.scanner.addObserver(this);
        scanner.start();
        System.out.println(this.projectList);
    }

    public GroupList getGroupLists() {
        return groupList;
    }

    public ProjectList getProjectList() {
        return projectList;
    }

    public ArrayList<String> getEventList() {
        return eventList;
    }

    @Override
    public void receiveNotification(Observable observable, String event) {
        eventList.add(event);
        System.out.println("Event envoye = "+event);
    }

    @Override
    public void receiveNotification(Observable observable, ArrayList<String> events) {
        for (String event : events) {
            eventList.add(event);
            System.out.println("Event envoye = "+event);
        }


    }
}
