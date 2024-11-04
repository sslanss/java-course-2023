package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();

    private boolean isWorked;

    public void setWorked(boolean isWorked) {
        this.isWorked = isWorked;
    }

    @Override
    public void execute(String command) {
        if (isWorked) {
            isWorked = new Random().nextBoolean();
        } else {
            isWorked = new Random().nextBoolean();
            throw new ConnectionException("Connection was interrupted");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Connection is closed");
    }
}
