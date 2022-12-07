package pl.mcprok.day3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetIntersectionTest extends Base {


    @ParameterizedTest
    @CsvSource({"vJrwpWtwJgWrhcsFMMfFFhFp,16", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,38", "PmmdzqPrVvPwwTWBwg,42"})
    void findPriorityOfSetIntersection(String s, String result) {

        int priority = new SetIntersection().findPriorityOfSetIntersection(s);

        assertEquals(priority, Integer.valueOf(result));
    }

    @Test
    void part1() throws Exception {
        List<String> lines = getLinesFromInput("day3/input.txt");
        SetIntersection setIntersection = new SetIntersection();

        int sum = lines.stream()
                .map(setIntersection::findPriorityOfSetIntersection).mapToInt(x -> x).sum();

        System.out.println(sum);
    }

    @Test
    void part2() throws URISyntaxException, IOException {
        List<String> lines = getLinesFromInput("day3/input_part2.txt");
        SetIntersection setIntersection = new SetIntersection();

        int sum = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            sum += setIntersection.findPriorityOf3Sets(lines.get(i), lines.get(i + 1), lines.get(i + 2));
        }
        System.out.println(sum);
    }

}