package com.houvet.Pattern;

import java.util.ArrayList;

public interface Observer {
    void receiveNotification(Observable observable, ArrayList<String> event);
    void receiveNotification(Observable observable, String event);
}