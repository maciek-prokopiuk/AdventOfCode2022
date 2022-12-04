package pl.mcprok.day2;


import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class SumOfPointsTest {

    @ParameterizedTest
    @ValueSource(strings = { "day2/simple.txt", "day2/input.txt"})
    void maxSumSimple(String filename) throws Exception {
        URL res = getClass().getClassLoader().getResource(filename);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        List<String> lines = Files.lines(Path.of(absolutePath)).collect(Collectors.toList());


        Part1 main = new Part1();
        List<Pair<Figure, Figure>> preprocess = main.preprocess(lines);
        System.out.println(main.sumOfPoints(preprocess));
    }

    @ParameterizedTest
    @ValueSource(strings = { "day2/simple.txt", "day2/input.txt"})
    void part2(String filename) throws Exception {
        URL res = getClass().getClassLoader().getResource(filename);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        List<String> lines = Files.lines(Path.of(absolutePath)).collect(Collectors.toList());


        var main = new Part2();
        var preprocess = main.preprocess(lines);
        System.out.println(main.sumOfPoints(preprocess));
    }

}