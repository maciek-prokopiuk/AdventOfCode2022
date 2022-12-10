package pl.mcprok.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CPUCycles extends Base {

    @ParameterizedTest
    @CsvSource({"day10/simple.txt,13140", "day10/input.txt,0"})
    public void part1(String filename, String expectedSum) throws URISyntaxException, IOException {
        int result = countSignalStrength(getLinesFromInput(filename));

        assertEquals(Integer.parseInt(expectedSum), result);
    }

    @ParameterizedTest
    @CsvSource({"day10/simple.txt", "day10/input.txt"})
    public void part2(String filename) throws URISyntaxException, IOException {
        printMessage(getLinesFromInput(filename));
    }

    public int countSignalStrength(List<String> lines) {
        var preprocessed = preprocess(lines);

        var interesting = Set.of(20, 60, 100, 140, 180, 220);

        var sumOfSignals = 0;
        var i = 0;
        var register = 1;
        for (String line : preprocessed) {
            i++;

            if(i > 220) return sumOfSignals;

            if (interesting.contains(i)) {
                sumOfSignals += register * i;
            }

            String[] splitted = line.split(" ");
            register += switch (splitted[0]) {
                case "noop" -> 0;
                case "addx" -> Integer.parseInt(splitted[1]);
                default -> throw new UnsupportedOperationException();
            };
        }
        return sumOfSignals;
    }

    public void printMessage(List<String> lines) {
        var preprocessed = preprocess(lines);

        var i = 0;
        var register = 1;
        var sb = new StringBuilder();
        for (String line : preprocessed) {

            sb.append(Math.abs(register-i)<2? "#" : ".");

            register += addx(line);

            if(++i == 40) {
                i=0;
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private int addx(String line) {
        String[] splitted = line.split(" ");
        return switch (splitted[0]) {
            case "noop" -> 0;
            case "addx" -> Integer.parseInt(splitted[1]);
            default -> throw new UnsupportedOperationException();
        };
    }


    public List<String> preprocess(List<String> lines) {
        return lines.stream()
                .flatMap(line -> switch (line.split(" ")[0]) {
                    case "addx" -> Stream.of("noop", line);
                    default -> Stream.of(line);
                })
                .toList();

    }

    public static final String SIMPLE = """
            addx 15
            addx -11
            addx 6
            addx -3
            addx 5
            addx -1
            addx -8
            addx 13
            addx 4
            noop
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx -35
            addx 1
            addx 24
            addx -19
            addx 1
            addx 16
            addx -11
            noop
            noop
            addx 21
            addx -15
            noop
            noop
            addx -3
            addx 9
            addx 1
            addx -3
            addx 8
            addx 1
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -36
            noop
            addx 1
            addx 7
            noop
            noop
            noop
            addx 2
            addx 6
            noop
            noop
            noop
            noop
            noop
            addx 1
            noop
            noop
            addx 7
            addx 1
            noop
            addx -13
            addx 13
            addx 7
            noop
            addx 1
            addx -33
            noop
            noop
            noop
            addx 2
            noop
            noop
            noop
            addx 8
            noop
            addx -1
            addx 2
            addx 1
            noop
            addx 17
            addx -9
            addx 1
            addx 1
            addx -3
            addx 11
            noop
            noop
            addx 1
            noop
            addx 1
            noop
            noop
            addx -13
            addx -19
            addx 1
            addx 3
            addx 26
            addx -30
            addx 12
            addx -1
            addx 3
            addx 1
            noop
            noop
            noop
            addx -9
            addx 18
            addx 1
            addx 2
            noop
            noop
            addx 9
            noop
            noop
            noop
            addx -1
            addx 2
            addx -37
            addx 1
            addx 3
            noop
            addx 15
            addx -21
            addx 22
            addx -6
            addx 1
            noop
            addx 2
            addx 1
            noop
            addx -10
            noop
            noop
            addx 20
            addx 1
            addx 2
            addx 2
            addx -6
            addx -11
            noop
            noop
            noop
            """;

}
