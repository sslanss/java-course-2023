package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();

    private boolean isWorked = new Random().nextBoolean();

    public void setWorked(boolean isWorked) {
        this.isWorked = isWorked;
    }
    @Override
    public void execute(String command) {
        if (isWorked) {
            System.out.println(command);
        }
        else {
            throw new ConnectionException("Connection was interrupted");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Connection is closed");
    }
}
