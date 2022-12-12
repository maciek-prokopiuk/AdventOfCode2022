package pl.mcprok.day11;

import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Monkeys extends Base {

    @Test
    public void test() throws URISyntaxException, IOException {
        List<String> linesFromInput = getLinesFromInput("day11/input.txt");
        List<Monkey> monkeys = new ArrayList<>();
        int inputSize = linesFromInput.size();
        for (int i = 0; i < inputSize; ) {
            var items = Arrays.stream(linesFromInput.get(i++).split(", "))
                    .map(Long::parseLong)
                    .toList();
            var operation = getOperation(linesFromInput.get(i++));
            var divider = Integer.parseInt(linesFromInput.get(i++));
            var throwValues = linesFromInput.get(i++).split(" ");
            monkeys.add(new Monkey(new ArrayDeque<Long>(items), operation, divider,
                    Integer.parseInt(throwValues[0]),
                    Integer.parseInt(throwValues[1])));
        }

        assertEquals(16792940265L, findMonkeyProcessedMostItems(monkeys));
    }

    private Function<Long, Long> getOperation(String op) {
        String[] splitted = op.split(" ");

        if (splitted[0].equals("*")) {
            if (splitted[1].equals("old")) {
                return x -> x * x;
            } else {
                return x -> x * Long.parseLong(splitted[1]);
            }
        } else {
            if (splitted[1].equals("old")) {
                return x -> x + x;
            } else {
                return x -> x + Long.parseLong(splitted[1]);
            }
        }
    }

    public long findMonkeyProcessedMostItems(List<Monkey> monkeys) {
        int minModulo = monkeys.stream().map(Monkey::getDivider).mapToInt(x -> x).reduce(1, (a, b) -> a * b);

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                var monkey = monkeys.get(j);
                var items = monkey.getItems();
                while (!items.isEmpty()) {
                    long old = items.poll();
                    var newWorryLevelPart2 = monkey.newWorryLevelPart2(old, minModulo);
                    //var newWorryLevelPart1 = monkey.newWorryLevel(old);
                    if (monkey.test(newWorryLevelPart2)) {
                        monkeys.get(monkey.getTrueValue()).getItems().offerLast(newWorryLevelPart2);
                    } else {
                        monkeys.get(monkey.getFalseValue()).getItems().offerLast(newWorryLevelPart2);
                    }
                }
            }
        }

        var twoMostActiveMonkeysCounters = monkeys.stream()
                .map(Monkey::getOpCounter)
                .sorted(Comparator.reverseOrder())
                .limit(2).toList();

        return (long) twoMostActiveMonkeysCounters.get(0) * twoMostActiveMonkeysCounters.get(1);
    }

}
