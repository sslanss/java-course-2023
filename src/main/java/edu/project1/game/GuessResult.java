package edu.project1.game;

public enum GuessResult {
    DEFEAT("Oops! You have just hanged!"),
    SUCCESSFUL_GUESS("You are right!"),
    FAILED_GUESS("You are wrong!"),
    WIN("You wan and abolished hanging!");

    final private String comment;

    GuessResult(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
