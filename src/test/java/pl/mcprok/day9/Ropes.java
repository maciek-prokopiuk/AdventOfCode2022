package pl.mcprok.day9;

import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ropes extends Base {

    @Test
    public void part1() throws URISyntaxException, IOException {
        assertEquals(6266, ropeWithSingleKnot());
    }

    @Test
    public void part2() throws URISyntaxException, IOException {
        assertEquals(2369, ropeWithTenKnots());
    }

    private int ropeWithSingleKnot() throws URISyntaxException, IOException {
        var simple = """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2
                """;

        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);

        var visited = new HashSet<Point>();

        List<String> input = getLinesFromInput("day9/input.txt");
        for (String line : input) {
            String[] splitted = line.split(" ");
            var move = getDirection(splitted[0]);
            for (int i = 0; i < Integer.parseInt(splitted[1]); i++) {
                head = move(head, move);
                tail = follow(head, tail);
                visited.add(tail);
            }
        }

        return visited.size();
    }

    private int ropeWithTenKnots() throws URISyntaxException, IOException {
        var simple = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
                                """;

        var points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(0, 0);
        }

        var visitedByLastKnot = new HashSet<Point>();
        visitedByLastKnot.add(new Point(0, 0));

        List<String> input = getLinesFromInput("day9/input.txt");
        for (String line : input) {
            String[] splitted = line.split(" ");
            var move = getDirection(splitted[0]);
            for (int i = 0; i < Integer.parseInt(splitted[1]); i++) {

                points[0] =  move(points[0], move); // move head

                for (int j = 1; j < 10; j++) { // move ther knots
                    var next = points[j];
                    var previous = points[j - 1];

                    points[j] = follow(previous, next);
                }

                Point tail = points[9];
                visitedByLastKnot.add(tail);
            }
        }

        return visitedByLastKnot.size();
    }

    private Point move(Point head, Point move) {
        return new Point(head.x() + move.x(), head.y() + move.y());
    }

    private Point follow(Point head, Point tail) {
        if (isAdjascent(head, tail)) {
            return tail;
        }

        var moveX = Integer.signum(head.x() - tail.x());
        var moveY = Integer.signum(head.y() - tail.y());
        return new Point(tail.x() + moveX, tail.y() + moveY);
    }

    private boolean isAdjascent(Point head, Point tail) {
        var dx = Math.abs(head.x() - tail.x());
        var dy = Math.abs(head.y() - tail.y());

        return dx <= 1 && dy <= 1;
    }

    private Point getDirection(String s) {
        return switch (s) {
            case "U" -> new Point(0, 1);
            case "D" -> new Point(0, -1);
            case "L" -> new Point(-1, 0);
            case "R" -> new Point(1, 0);
            default -> throw new UnsupportedOperationException();
        };
    }

}