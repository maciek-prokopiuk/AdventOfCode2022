package pl.mcprok.day2;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public int sumOfPoints(List<Pair<Figure, Figure>> figures) {
        int sum = 0;
        for (Pair<Figure, Figure> pair : figures) {
            if(pair.getLeft() == pair.getRight()) {
                sum += 3; // draw

            } else if(pair.getRight().beat(pair.getLeft())){
                sum += 6; // win
            }

            sum += pair.getRight().points(); // points for figure
        }

        return sum;
    }

    public List<Pair<Figure, Figure>> preprocess(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    String[] splitted = line.split(" ");
                    return Pair.of(getFigure(splitted[0]), getFigure(splitted[1]));
                })
                .collect(Collectors.toList());
    }

    private Figure getFigure(String s) {
        return switch (s) {
            case "A", "X" -> Figure.ROCK;
            case "B", "Y" -> Figure.PAPER;
            case "C", "Z" -> Figure.SCISSORS;
            default -> throw new IllegalArgumentException();
        };
    }
}
