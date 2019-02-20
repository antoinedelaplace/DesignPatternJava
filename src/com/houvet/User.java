package com.houvet;

import java.io.Serializable;

/**
 * Created by benoit-xavierhouvet on 21/02/2017.
 */
public class User extends Commentable implements Serializable {
    private String name;
    private String lastName;
    private String phone;
    private String mail;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getMail() { return mail; }

    public void setMail(String main) { this.mail = mail; }

    public User(String name, String lastName, String phone, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return (this.name);
    }




}
