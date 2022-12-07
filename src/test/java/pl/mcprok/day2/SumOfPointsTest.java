package pl.mcprok.day2;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.mcprok.utils.Base;

class SumOfPointsTest extends Base {

    @ParameterizedTest
    @ValueSource(strings = { "day2/simple.txt", "day2/input.txt"})
    void part1(String filename) throws Exception {
        var main = new Part1();
        var preprocess = main.preprocess(getLinesFromInput(filename));
        System.out.println(main.sumOfPoints(preprocess));
    }

    @ParameterizedTest
    @ValueSource(strings = { "day2/simple.txt", "day2/input.txt"})
    void part2(String filename) throws Exception {
        var main = new Part2();
        var preprocess = main.preprocess(getLinesFromInput(filename));
        System.out.println(main.sumOfPoints(preprocess));
    }

}