package pl.mcprok.day12;

import org.junit.jupiter.api.Test;
import pl.mcprok.day9.Point;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HillClimbingAkaBFS extends Base {

    @Test
    public void test() throws URISyntaxException, IOException {
        var grid = prepareGrid("day12/input.txt");
        System.out.println(grid.findShortestPath());
    }

    public Grid prepareGrid(String inputFile) throws URISyntaxException, IOException {
        List<String> input = getLinesFromInput(inputFile);
        Point start = null;
        Set<Point> endPositions = new HashSet<>();

        var grid = new char[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i).toCharArray();
            for (int j = 0; j < line.length; j++) {
                char c = line[j];
                // we will be looking the route from the end to the beginning to handle part2 in one pass
                if (c == 'E') {
                    start = new Point(j, i);
                    grid[i][j] = 'z';
                } else if (c == 'S' || c == 'a') {
                    endPositions.add(new Point(j, i));
                    grid[i][j] = 'a';
                } else {
                    grid[i][j] = c;
                }
            }
        }

        return new Grid(grid, start, endPositions);
    }

    static final class Grid {
        public static final List<Point> DIRECTIONS = List.of(new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0));
        private final char[][] grid;
        private final Point startingPoint;
        private final Set<Point> endPositions;
        private final Set<Point> visited = new HashSet<>();
        private final Deque<Pair> queue = new ArrayDeque<>();
        private final int[][] distances;

        Grid(char[][] grid, Point startingPoint, Set<Point> endPositions) {
            this.grid = grid;
            this.startingPoint = startingPoint;
            this.endPositions = endPositions;
            this.distances = new int[grid.length][grid[0].length];
        }

        private void addNeighbours(Pair current) {
            Point parent = current.point;
            for (Point dir : DIRECTIONS) {
                int newX = parent.x() + dir.x();
                int newY = parent.y() + dir.y();
                var next = new Point(newX, newY);
                if (!visited.contains(next) && isInGrid(next) && highDiffAtMostOne(parent, next)) {
               // System.out.println("Adding x=%s, y=%s, from x=%s, y=%s".formatted(next.x(), next.y(), parent.x(), parent.y()));
                    queue.addLast(new Pair(next, current.distance() + 1));
                    distances[next.y()][next.x()] = current.distance() +1;
                    visited.add(next);
                }
            }
        }

        private boolean isInGrid(Point possibleNextPoint) {
            int x = possibleNextPoint.x();
            int y = possibleNextPoint.y();
            return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
        }

        private boolean highDiffAtMostOne(Point parent, Point current) {
            return grid[parent.y()][parent.x()] - 1 ==  grid[current.y()][current.x()] ||
                    grid[parent.y()][parent.x()] <=  grid[current.y()][current.x()];
        }

        public int findShortestPath() {
            queue.add(new Pair(startingPoint, 0));
            visited.add(startingPoint);
            distances[startingPoint.y()][startingPoint.x()] = 0;

            var minEndPositionLenghts = new ArrayList<Integer>();
            while (!queue.isEmpty()) {
                var current = queue.pollFirst();
                if (endPositions.contains(current.point)) {
                    minEndPositionLenghts.add(current.distance);
                }

                addNeighbours(current);
            }
            var sb = new StringBuilder();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    sb.append(distances[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            return minEndPositionLenghts.stream().sorted().findFirst().get();
        }
    }

    record Pair(Point point, int distance) {
    }

}
