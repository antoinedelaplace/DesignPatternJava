package com.houvet.UI.Panel;

import com.houvet.Commentable;
import com.houvet.Message;
import com.houvet.UI.Window.MainWindow;
import javax.swing.*;
import java.awt.*;

public class AddMessagePanel extends FormPanel {

    private JTextField author;
    private JTextArea text;
    private String previousPanel;
    private Commentable commentable;

    /**
     * @param main window caller
     */
    public AddMessagePanel(MainWindow main, String previousPanel, Commentable commentable) {
        super(main);
        this.previousPanel = previousPanel;
        this.commentable = commentable;
    }

    /**
     * Create frame content
     * Add fields to create a message
     * Add panel for button Add and Back
     * On click to add, message is created and you return to previous panel
     */
    @Override
    protected void init()  {
        this.author = new JTextField();
        JLabel authorLabel = new JLabel("Author");
        this.text = new JTextArea();
        JLabel textLabel = new JLabel("Message");
        JPanel fieldsPanel = new JPanel();

        author.setPreferredSize(new Dimension(500, 30));
        text.setPreferredSize(new Dimension(500, 500));
        fieldsPanel.add(authorLabel);
        fieldsPanel.add(author);
        fieldsPanel.add(textLabel);
        fieldsPanel.add(text);
        this.setLayout(new BorderLayout());
        this.add(fieldsPanel,BorderLayout.CENTER);
        super.init();
    }

    /**
     * On click to back button, you return to previous panel
     */
    void backAction() {
        this.mainWindow.changePanel(this.previousPanel);
    }

    /**
     * On click to add button, message is created and you return to previous panel
     */
    void addAction() {
        Message message = new Message(author.getText(), text.getText());
        this.commentable.comment(message);
        this.mainWindow.changePanel(previousPanel);
    }
}
