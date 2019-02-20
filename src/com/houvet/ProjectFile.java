package com.houvet;

import java.io.Serializable;

/**
 * Created by benoit-xavierhouvet on 01/03/2017.
 */
public class ProjectFile extends Commentable implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectFile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
