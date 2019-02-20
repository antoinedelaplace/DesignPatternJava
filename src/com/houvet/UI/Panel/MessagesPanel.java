package com.houvet.UI.Panel;

import com.houvet.Commentable;
import com.houvet.Message;
import com.houvet.UI.Window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {

    private MainWindow mainWindow;
    private Commentable commentable;
    private JPanel messages;
    private JButton add;

    /**
     * @param main window caller
     */
    public MessagesPanel(MainWindow main) {
        this.mainWindow = main;
        this.setLayout(new BorderLayout());
        this.init();
    }

    /**
     * create
     * add navigation button
     */
    private void init() {
        JPanel buttonsPanel = new JPanel();
        JLabel title = new JLabel("Messages");

        this.add = new JButton("Add");
        this.messages = new JPanel();
        add.addActionListener(e -> this.mainWindow.addMessage(this.commentable));
        buttonsPanel.add(add);
        add.setVisible(false);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(messages, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(title, BorderLayout.NORTH);
    }

    public void displayAddButton() {
        this.add.setVisible(true);
    }

    public void hideAddButton() {
        this.add.setVisible(false);
    }

    public void displayMessages(Commentable commentable) {
        this.hideMessages();
        this.commentable = commentable;
        for (Message message : this.commentable.getMessages()) {
            JLabel messageLabel = new JLabel(message.getAuthor()+ " : "+message.getText(), new ImageIcon("images/message.png"), JLabel.CENTER);
            messageLabel.setPreferredSize(new Dimension(500, 30));
            messages.add(messageLabel);
        }
    }

    public void hideMessages() {
        this.messages.removeAll();
        this.messages.revalidate();
        this.messages.repaint();
    }
}
