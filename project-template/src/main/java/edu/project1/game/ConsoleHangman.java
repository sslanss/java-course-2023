package edu.project1.game;

import edu.project1.dictionaries.AnimalsDictionary;
import edu.project1.dictionaries.CountriesDictionary;
import edu.project1.dictionaries.Dictionary;
import edu.project1.dictionaries.FillingsDictionary;
import edu.project1.dictionaries.TransportDictionary;
import edu.project1.handlers.GameOutputHandler;
import edu.project1.handlers.PlayerInputHandler;
import org.jetbrains.annotations.NotNull;

public class ConsoleHangman {
    private final GameOutputHandler gameOutput;
    private final PlayerInputHandler playerInput;
    private boolean isExit;

    public ConsoleHangman() {
        gameOutput = new GameOutputHandler();
        playerInput = new PlayerInputHandler();
        isExit = false;
    }

    public ConsoleHangman(GameOutputHandler gameOutput, PlayerInputHandler playerInput) {
        this.gameOutput = gameOutput;
        this.playerInput = playerInput;
        isExit = false;
    }

    public boolean getIsExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    private @NotNull String generateHiddenWord(int category) {
        Dictionary dictionary;
        switch (category) {
            case 1:
                dictionary = new AnimalsDictionary();
                break;
            case 2:
                dictionary = new CountriesDictionary();
                break;
            case 3:
                dictionary = new FillingsDictionary();
                break;
            default:
                dictionary = new TransportDictionary();
                break;
        }
        return dictionary.randomWord();
    }

    private boolean runSession(GameSession session) {
        gameOutput.displayGameStatus(session.getUserAnswer(), session.getAvailableLetters(),
            session.getAvailableAttempts());
        char supposedLetter = playerInput.getSupposedLetter(session.getAvailableLetters());
        GuessResult guessResult;
        if (supposedLetter == ' ') {
            guessResult = session.giveUp();
        } else {
            guessResult = session.tryGuess(supposedLetter);
        }
        gameOutput.displayGuessResult(guessResult);
        return guessResult.equals(GuessResult.DEFEAT) || guessResult.equals(GuessResult.WIN);
    }

    public void run() {
        while (!isExit) {
            int category = playerInput.getWordCategory();
            String hiddenWord = generateHiddenWord(category);
            GameSession session = new GameSession(hiddenWord);
            boolean isEndOfSession = false;
            while (!isEndOfSession) {
                isEndOfSession = runSession(session);
            }
            char answer = playerInput.getAnswer();
            if (answer == 'N' || answer == 'n') {
                isExit = true;
            }
        }
    }
}

