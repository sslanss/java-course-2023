package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {

    private final ConnectionManager manager;

    private final int maxAttempts;

    private final static Logger LOGGER = LogManager.getLogger();

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void tryExecute(String command) {
        int currentAttempts = 0;
        ConnectionException lastException = null;
        while (currentAttempts != maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                LOGGER.info(e.getMessage());
                lastException = e;
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            currentAttempts++;
        }
        try {
            throw new ConnectionException("Connection error", lastException);
        } catch (ConnectionException e) {
            LOGGER.info(e.getMessage() + " at: " + e.getCause());
        }
    }

}
