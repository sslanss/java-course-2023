package edu.hw3;

import edu.hw3.task6.MyStockMarket;
import edu.hw3.task6.Stock;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyStockMarketTest {
    private static Stock stock1;
    private static Stock stock2;
    private static Stock stock3;

    private MyStockMarket stockMarket;

    @BeforeAll
    public static void  setUpStocks() {
        stock1 = new Stock("Ааа", 101.5);
        stock2 = new Stock("ППП", 151.7);
        stock3 = new Stock("Ууу", 57.3);
    }

    @BeforeEach
    public void setUpStockMarket() {
        stockMarket = new MyStockMarket();
    }


    @Test
    public void testAdd() {
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        assertThat(stockMarket.getStocks().size()).isEqualTo(3);
    }

    @Test
    public void testRemove() {
        stockMarket.add(stock1);
        stockMarket.remove(stock1);
        assertThat(stockMarket.getStocks().size()).isEqualTo(0);
    }

    @Test
    public void testMostValuableStock() {
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        Stock mostValuable = stockMarket.mostValuableStock();
        assertThat(mostValuable).isEqualTo(stock2);
        stockMarket.remove(stock2);
        mostValuable = stockMarket.mostValuableStock();
        assertThat(mostValuable).isEqualTo(stock1);
    }
}
