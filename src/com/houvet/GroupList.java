package com.houvet;

import com.houvet.Pattern.Observable;
import com.houvet.Pattern.Observer;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.*;

/**
 * Created by benoit-xavierhouvet on 26/02/2017.
 */
public class GroupList implements Serializable, Observable {

    private ArrayList<Group> Groups = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    private String PathGroups = "data/json/group.json";

    public ArrayList<Group> getGroups() {
        return Groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        Groups = groups;
    }

    public boolean addGroup(Group group) throws IOException{
        for (Group grp : Groups) {
            if (grp.getName().equals(group.getName()))
                return false;
        }
        Groups.add(group);
        this.notifyObservers("Le groupe "+group.getName()+" a été créé");
        saveGroup();
        return true;
    }

    public ArrayList<User> getUsers() {
        for (Group grp : Groups) {
            if (grp.getName().equals("all"))
                return grp.getUsers();
        }
        return (new ArrayList<User>());
    }

    public boolean removeGroup(Group group) throws IOException {
        if (group.getName().equals("all"))
            return false;
        for (Group grp : Groups) {
            if (grp.getName().equals(group.getName())) {
                Groups.remove(grp);
                this.notifyObservers("Le groupe "+group.getName()+" a été supprimé");
                saveGroup();
                return true;
            }
        }
        return false;
    }

    public boolean addUser(User user, Group group) throws IOException {
        for (Group grp : Groups) {
            if (grp.getName().matches("all")) {
                if (!grp.addUser(user))
                    return false;
            }
        }
        for (Group grpe : Groups) {
            if (grpe.getName().matches(group.getName())) {
                if (!grpe.addUser(user))
                    return false;
                this.notifyObservers("Le user "+user.getName()+" a été créé et ajouté au groupe "+group.getName());
            }
        }
        saveGroup();
        return true;
    }

    public boolean addUserToGroup(User user, Group group) throws IOException {
        for (Group grp : Groups) {
            if (grp.getName().equals(group.getName())) {
                if (!grp.addUser(user))
                    return false;
                this.notifyObservers("Le user "+user.getName()+" a été ajouté au groupe "+group.getName());
            }
        }
        saveGroup();
        return true;
    }

    public boolean removeUser(User user) throws IOException {
        for (Group grp : Groups) {
            grp.removeUser(user);
        }
        this.notifyObservers("Le user "+user.getName()+" a été supprimé");
        saveGroup();
        return true;
    }

    public boolean removeUserFromGroup(User user, Group group) throws IOException {
        if (group.getName().equals("all"))
            return removeUser(user);
        else {
            for (Group grp : Groups) {
                if (grp.getName().equals(group.getName())) {
                    grp.removeUser(user);
                    this.notifyObservers("Le user "+user.getName()+" a été enlevé du groupe "+group.getName());
                }
            }
        }
        saveGroup();
        return true;
    }

    public void saveGroup() throws IOException {
        try {
            File file = new File(this.PathGroups);
            file.delete();
            file.createNewFile();
            FileOutputStream fichier = new FileOutputStream(this.PathGroups);
            ObjectOutputStream group = new ObjectOutputStream(fichier);
            group.writeObject(getGroups());
        }
        catch (Exception ex) {

        }
    }

    public void loadGroup() throws IOException {
        try {
        FileInputStream file = new FileInputStream(this.PathGroups);
        ObjectInputStream group = new ObjectInputStream(file);
        ArrayList<Group> groups = (ArrayList<Group>) group.readObject();
        setGroups(groups);
        }
        catch (Exception ex) {
        }
    }

    public GroupList() throws IOException {
        loadGroup();
        addGroup(new Group("all", "all", null));
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