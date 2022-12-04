package pl.mcprok.day2;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Part2 {


    public int sumOfPoints(List<Pair<Figure, Result>> figures) {
        int sum = 0;
        for (Pair<Figure, Result> pair : figures) {
            sum += switch (pair.getRight()) {
                case WIN -> pair.getLeft().loseTo().points();
                case LOSE -> pair.getLeft().winTo().points();
                case DRAW -> pair.getLeft().drawTo().points();
            };

            sum+= pair.getRight().points();
        }

        return sum;
    }

    public List<Pair<Figure, Result>> preprocess(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    String[] splitted = line.split(" ");
                    return Pair.of(getFigure(splitted[0]), getResult(splitted[1]));
                })
                .collect(Collectors.toList());
    }

    private Result getResult(String s) {
        return switch (s) {
            case "X" -> Result.LOSE;
            case "Y" -> Result.DRAW;
            case "Z" -> Result.WIN;
            default -> throw new IllegalArgumentException();
        };
    }

    private Figure getFigure(String s) {
        return switch (s) {
            case "A" -> Figure.ROCK;
            case "B" -> Figure.PAPER;
            case "C" -> Figure.SCISSORS;
            default -> throw new IllegalArgumentException();
        };
    }
}
