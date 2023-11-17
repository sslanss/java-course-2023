package edu.project2.solvers;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFSSolver extends Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        visited = new boolean[maze.getHeight()][maze.getWidth()];
        nextToVisit.add(start);
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        parentMap.put(start, null);
        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();
            if (!maze.isValidLocation(cur) || isExplored(cur)) {
                continue;
            }
            if (maze.isWall(cur)) {
                setVisited(cur);
                continue;
            }
            if (cur.equals(end)) {
                return backtrackPath(start, cur, parentMap);
            }
            for (int[] direction : DIRECTIONS) {
                int newRow = cur.row() + direction[0];
                int newCol = cur.col() + direction[1];
                Coordinate neighbor = new Coordinate(newRow, newCol);
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, cur);
                    nextToVisit.add(neighbor);
                }
                setVisited(cur);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate start, Coordinate end, Map<Coordinate, Coordinate> parentMap) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;
        while (current != null && !current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        if (current != null) {
            path.add(start);
            Collections.reverse(path);
        }
        return path;
    }
}
