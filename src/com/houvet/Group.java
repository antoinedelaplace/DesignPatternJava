package com.houvet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoit-xavierhouvet on 21/02/2017.
 */
public class Group extends Commentable implements Serializable {

    private String name;
    private String description;
    private ArrayList<User> users = new ArrayList<>();

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ArrayList<User> getUsers() { return users; }

    public void setUsers(ArrayList<User> users) { this.users = users; }

    public Group(String name, String description, ArrayList<User> users) {
        this.name = name;
        this.description = description;
        if (users == null)
            this.users = new ArrayList<User>();
        else
            this.users = users;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        this.users = new ArrayList<User>();
    }

    public boolean addUser(User user) {
        for (User usr : users) {
            if (usr.getName().equals(user.getName()) && usr.getLastName().equals(user.getLastName()))
                return false;
            if (usr.getMail().equals(user.getMail()))
                return false;
        }
        users.add(user);
        return true;
    }

    public boolean removeUser(User user) {
        for (User usr : users) {
            if (usr.getName().equals(user.getName()) && usr.getLastName().equals(user.getLastName())) {
                users.remove(usr);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
