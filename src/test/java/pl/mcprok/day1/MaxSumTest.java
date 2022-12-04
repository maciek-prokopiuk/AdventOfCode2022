package pl.mcprok.day1;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class MaxSumTest {

    @Test
    void maxSum() throws Exception {
        URL res = getClass().getClassLoader().getResource("day1/input.txt");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        List<String> collect = Files.lines(Path.of(absolutePath)).collect(Collectors.toList());


        System.out.println(new MaxSum().maxSum(collect));
    }


    @Test
    void sumOfTop3() throws Exception {
        URL res = getClass().getClassLoader().getResource("day1/input.txt");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        List<String> collect = Files.lines(Path.of(absolutePath)).collect(Collectors.toList());


        System.out.println(new MaxSum().maxSumOf3TopElements(collect));
    }
}