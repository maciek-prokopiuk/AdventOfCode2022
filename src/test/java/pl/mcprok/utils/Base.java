package pl.mcprok.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Base {

    public List<String> getLinesFromInput(String inputFile) throws URISyntaxException, IOException {
        URL res = this.getClass().getClassLoader().getResource(inputFile);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        List<String> lines = Files.lines(Path.of(absolutePath)).collect(Collectors.toList());
        return lines;
    }
}