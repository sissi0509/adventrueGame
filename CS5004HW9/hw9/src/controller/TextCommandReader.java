package controller;

import view.ITextView;

/**
 * This is a helper class that reads the user's text input.
 */
public class TextCommandReader implements ITextCommandReader {
    private String command;
    private String answer;
    private String userName;
    private ITextView view;

    public TextCommandReader(ITextView view) {
        this.command = "";
        this.answer = "";
        this.userName = "";
        this.view = view;
    }

    @Override
    public void getUserName() {
        this.userName = view.promptUser("Welcome to the adventure game!\nEnter a name for your player avatar: ");
        view.showResult("You shalt now be named: " + this.userName + "\n\n");
    }

    @Override
    public void getUserCommand() {
        String choice = this.view.promptUser("Your choice: ");
        String[] words = choice.split(" ", 2);
        this.command = words[0].toUpperCase();
        if (words.length == 2) {
            this.answer = words[1].toUpperCase().trim();
        }
    }

    @Override
    public void setAnswerEmpty() {
        this.answer = "";
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    @Override
    public String getName() {
        return this.userName;
    }
}
