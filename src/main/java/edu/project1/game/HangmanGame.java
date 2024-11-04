package edu.project1.game;

public class HangmanGame {
    private HangmanGame() {

    }
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        ConsoleHangman hangman = new ConsoleHangman();
        hangman.run();
    }
}
