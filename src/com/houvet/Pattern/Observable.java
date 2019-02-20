package com.houvet.Pattern;


import java.util.ArrayList;

public interface Observable {
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObservers(ArrayList<String> event);
    void notifyObservers(String event);
}
