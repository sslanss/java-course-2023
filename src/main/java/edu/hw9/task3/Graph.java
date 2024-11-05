package edu.hw9.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Graph {
    private final Map<Integer, List<Integer>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addEdge(int source, int destination) {
        adjVertices.computeIfAbsent(source, k -> new ArrayList<>());
        adjVertices.computeIfAbsent(destination, k -> new ArrayList<>());
        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source);
    }

    public List<Integer> dfs(int start, int end) {
        ConcurrentLinkedQueue<Integer> path = new ConcurrentLinkedQueue<>();
        AtomicBoolean[] visited = new AtomicBoolean[adjVertices.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new AtomicBoolean(false);
        }
        path.add(start);

        try (var service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            if (dfsWithConcurrency(start, end, visited, path, service)) {
                service.shutdown();
                return new ArrayList<>(path);
            }
            service.shutdown();
            return new ArrayList<>();
        }
    }

    private boolean dfsWithConcurrency(
        int current, int destination, AtomicBoolean[] visited,
        ConcurrentLinkedQueue<Integer> path, ExecutorService executor
    ) {
        visited[current].set(true);
        if (current == destination) {
            return true;
        }

        for (int dest : adjVertices.get(current)) {
            if (!visited[dest].get()) {
                path.add(dest);
                CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() ->
                    dfsWithConcurrency(dest, destination, visited, path, executor), executor);
                if (future.join()) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        //executorService.shutdown();///-не уверена
        return false;

    }
}

