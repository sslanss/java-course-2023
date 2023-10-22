package edu.hw2;

import edu.hw2.task3.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void faultyConnectionShouldThrowsExc() {
        boolean isThrown = false;
        try (FaultyConnection faultyConnection = new FaultyConnection()) {
            faultyConnection.setWorked(false);
            faultyConnection.execute("command");
        } catch (Exception e) {
            isThrown = true;
        }
        Assertions.assertThat(isThrown).isEqualTo(true);
    }

    @Test
    public void defaultConnectionManagerShouldCreateConnections() {
        DefaultConnectionManager manager = new DefaultConnectionManager();
        manager.setFaultyConnectionChance(1.1);
        Connection connection = manager.getConnection();
        Assertions.assertThat(connection.getClass()).isEqualTo(new FaultyConnection().getClass());

        manager = new DefaultConnectionManager();
        manager.setFaultyConnectionChance(-1.1);
        connection = manager.getConnection();
        Assertions.assertThat(connection.getClass()).isEqualTo(new StableConnection().getClass());
    }

}
