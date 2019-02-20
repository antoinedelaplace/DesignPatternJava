package com.houvet;

import com.houvet.UI.Window.MainWindow;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Dashboard dashboard = Dashboard.getInstance();
        MainWindow mainWindow = new MainWindow("Task Manager", 600, 800, dashboard);

    }
}
