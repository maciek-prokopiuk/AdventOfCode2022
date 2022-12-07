package pl.mcprok.day7;

import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileSystemTraversal extends Base {
    String simple = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """;

    @Test
    public void part1() throws URISyntaxException, IOException {
        var input = getLinesFromInput("day7/input.txt");

        var fileSystemTraversal = new FileSystemTraversal();
        fileSystemTraversal.buildTree(input);

        var result = fileSystemTraversal.sumOfAllFoldersWithSizeLessThan(100000);
        System.out.println(result);
    }

    @Test
    public void part2() throws URISyntaxException, IOException {
        var input = getLinesFromInput("day7/input.txt");

        var fileSystemTraversal = new FileSystemTraversal();
        fileSystemTraversal.buildTree(input);

        var result = fileSystemTraversal.smallestDirEnoughToUpdate();
        System.out.println(result);
    }

    private Dir root;
    private final Set<Dir> allFolders = new HashSet<>();

    public Dir buildTree(List<String> input) {
        int i = 1;

        root = new Dir("/", null);
        allFolders.add(root);

        var current = root;
        var currentLine = input.get(i);

        while(i<input.size()) {
            if(currentLine.startsWith("$")) {
                currentLine = input.get(i);
                String[] splitted = currentLine.split(" ");
                var command = splitted[1];
                if(command.equals("cd")) {
                    String dirName = splitted[2];
                    current = switch (dirName) {
                       case ".." -> current.getParent() != null ? current.getParent() : current;
                       case "/" -> root;
                       default -> current.findDir(dirName);
                    };
                    i++;
                } else {
                    //ls
                    currentLine = input.get(++i);
                    while(!currentLine.startsWith("$")) {
                        String[] file = currentLine.split(" ");
                        if(currentLine.startsWith("dir")) {
                            var name = file[1];
                            var newDir = new Dir(name, current);
                            current.addDir(newDir);
                            allFolders.add(newDir);
                        } else {

                            current.addFile(new File(file[1], Integer.parseInt(file[0])));
                        }
                        i++;
                        if(i == input.size()) {
                            break;
                        } else {
                            currentLine = input.get(i);
                        }
                    }
                }
            }
        }
        return root;
    }

    public int sumOfAllFoldersWithSizeLessThan(int maxSize) {
        return allFolders.stream()
                .mapToInt(Dir::getDirSize)
                .filter(dirSize -> dirSize <= maxSize)
                .sum();
    }

    public int smallestDirEnoughToUpdate() {
        var rootSize = root.getDirSize();
        var freeSpace = 70_000_000 - rootSize;
        var requiredSpace = 30_000_000 - freeSpace;

        return allFolders.stream()
                .mapToInt(Dir::getDirSize)
                .filter(x -> x >= requiredSpace)
                .min().getAsInt();
    }


}
