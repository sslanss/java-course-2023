package edu.hw3.task6;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyStockMarket implements StockMarket {

    private static final Comparator<Stock> STOCK_COMPARATOR = Comparator.comparingDouble(Stock::value).reversed();

    private final Queue<Stock> stocks = new PriorityQueue<>(11, STOCK_COMPARATOR);

    public Queue<Stock> getStocks() {
        return stocks;
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

}
