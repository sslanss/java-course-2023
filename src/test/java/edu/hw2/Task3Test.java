package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.StableConnection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void commandExecutorShouldThrowsExcIfConnectionWasNotMade() {
        DefaultConnectionManager manager = mock(DefaultConnectionManager.class);
        FaultyConnection connection = new FaultyConnection();
        when(manager.getConnection()).thenReturn(connection);
        connection.setWorked(false);
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(manager, 1);
        commandExecutor.updatePackages();
    }

}
