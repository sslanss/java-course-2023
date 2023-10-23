package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    private double faultyConnectionChance;

    public void setFaultyConnectionChance(double chance) {
        this.faultyConnectionChance = chance;
    }

    public DefaultConnectionManager() {
        faultyConnectionChance = 0.2;
    }

    @Override
    public Connection getConnection() {
        Random random = new Random();
        double chance = random.nextDouble(0, 1);
        if (chance <= faultyConnectionChance) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
