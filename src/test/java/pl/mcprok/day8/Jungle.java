package pl.mcprok.day8;

import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jungle extends Base {
    private final int[][] simpleGrid = new int[][]{
            {3, 0, 3, 7, 3},
            {2, 5, 5, 1, 2},
            {6, 5, 3, 3, 2},
            {3, 3, 5, 4, 9},
            {3, 5, 3, 9, 0}};

    @Test
    public void part1() throws URISyntaxException, IOException {
        var grid = new int[99][99];
        List<String> linesFromInput = getLinesFromInput("day8/input.txt");

        for (int i = 0; i < linesFromInput.size(); i++) {
            String line = linesFromInput.get(i);
            char[] chars = line.toCharArray();
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = chars[j] - 48;
            }
        }

        assertEquals(1825, countOfVisibleTrees(grid));
    }

    @Test
    public void part2() throws URISyntaxException, IOException {
        var grid = new int[99][99];
        List<String> linesFromInput = getLinesFromInput("day8/input.txt");

        for (int i = 0; i < linesFromInput.size(); i++) {
            String line = linesFromInput.get(i);
            char[] chars = line.toCharArray();
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = chars[j] - 48;
            }
        }

        assertEquals(235200, maxScenicScore(grid));
    }

    public static int countOfVisibleTrees(int[][] grid) {
        int sumOfVisibleTrees = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sumOfVisibleTrees += isVisible(i, j, grid) ? 1 : 0;
            }
        }

        return sumOfVisibleTrees;
    }

    public static int maxScenicScore(int[][] grid) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                var scenicScore = scenicScore(i, j, grid);
                max = Math.max(scenicScore, max);
            }
        }

        return max;
    }

    private static int scenicScore(int i, int j, int[][] grid) {
        // if on the edges then we do not need to look for the distances
        // because anything multiplied by 0 equal to 0
        if (i == 0 || i == grid.length - 1) {
            return 0;
        }
        // handle edges
        if (j == 0 || j == grid[i].length - 1) {
            return 0;
        }

        int current = grid[i][j];

        int up = scoreUp(i, j, grid, current);
        int down = scoreDown(i, j, grid, current);
        int left = scoreLeft(i, j, grid, current);
        var right = scoreRight(i,j,grid,current);

        return left * right * up * down;
    }

    private static int scoreRight(int i, int j, int[][] grid, int current) {
        var right = 0;
        for (int x = j + 1; x < grid[i].length; x++) {
            right++;
            if (grid[i][x] >= current) {
                break;
            }
        }
        return right;
    }

    private static int scoreLeft(int i, int j, int[][] grid, int current) {
        var left = 0;
        for (int x = j - 1; x >= 0; x--) {
            left++;
            if (grid[i][x] >= current) {
                break;
            }
        }
        return left;
    }

    private static int scoreDown(int i, int j, int[][] grid, int current) {
        var down = 0;
        for (int x = i + 1; x < grid.length; x++) {
            down++;
            if (grid[x][j] >= current) {
                break;
            }
        }
        return down;
    }

    private static int scoreUp(int i, int j, int[][] grid, int current) {
        var up = 0;
        for (int x = i - 1; x >= 0; x--) {
            up++;
            if (grid[x][j] > current) {
                break;
            }
        }
        return up;
    }

    private static boolean isVisible(int i, int j, int[][] grid) {
        // handle edges
        if (i == 0 || i == grid.length - 1) {
            return true;
        }
        // handle edges
        if (j == 0 || j == grid[i].length - 1) {
            return true;
        }

        int current = grid[i][j];
        // find max in any direction and then check if current is bigger than any found max
        return Stream.of(
                        range(0, i).map(x -> grid[x][j]).max().getAsInt(),
                        range(i + 1, grid.length).map(x -> grid[x][j]).max().getAsInt(),
                        range(0, j).map(x -> grid[i][x]).max().getAsInt(),
                        range(j + 1, grid[i].length).map(x -> grid[i][x]).max().getAsInt()
                )
                .anyMatch(max -> current > max);
    }

    @SuppressWarnings("unchecked")
    static IntStream reverse(IntStream input) {
        int[] temp = input.toArray();
        return range(0, temp.length)
                .map(i -> temp[temp.length - i - 1]);
    }

}
