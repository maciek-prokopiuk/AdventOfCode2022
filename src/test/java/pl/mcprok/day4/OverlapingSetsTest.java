package pl.mcprok.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OverlapingSetsTest extends Base {


    @ParameterizedTest
    @CsvSource({"2,4,6,8,false", "2,3,4,5,false", "5,7,7,9,false", "2,8,3,7,true", "6,6,4,6,true", "2,6,4,8,false"})
    void overlaps(String i, String j, String k, String l, String expectedResult) {

        var overlapingSets = new OverlapingSets();

        boolean result = overlapingSets.fullyOverlaps(parseInt(i), parseInt(j), parseInt(k), parseInt(l));

        assertEquals(Boolean.valueOf(expectedResult), result);
    }

    @Test
    void part1() throws URISyntaxException, IOException {
        var lines = getLinesFromInput("day4/input.txt");

        var overlapingSets = new OverlapingSets();

        var sum = lines.stream()
                .map(line -> {
                    String[] splitByComma = line.split(",");
                    String[] ij = splitByComma[0].split("-");
                    String[] kl = splitByComma[1].split("-");

                    return overlapingSets.fullyOverlaps(parseInt(ij[0]), parseInt(ij[1]), parseInt(kl[0]), parseInt(kl[1]));
                })
                .mapToInt(bool -> bool ? 1 : 0)
                .sum();
        System.out.println(sum);
    }

    @Test
    void part2() throws URISyntaxException, IOException {
        var lines = getLinesFromInput("day4/input.txt");

        var overlapingSets = new OverlapingSets();

        var sum = lines.stream()
                .map(line -> {
                    String[] splitByComma = line.split(",");
                    String[] ij = splitByComma[0].split("-");
                    String[] kl = splitByComma[1].split("-");

                    return overlapingSets.overlaps(parseInt(ij[0]), parseInt(ij[1]), parseInt(kl[0]), parseInt(kl[1]));
                })
                .mapToInt(bool -> bool ? 1 : 0)
                .sum();
        System.out.println(sum);
    }


}