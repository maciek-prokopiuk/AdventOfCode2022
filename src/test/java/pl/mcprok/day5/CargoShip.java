package pl.mcprok.day5;

import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Stack;

public class CargoShip {
    Utils utils = new Utils();

    @Test
    public void test() throws URISyntaxException, IOException {
        List<String> commands = utils.getLinesFromInput("day5/input.txt");

        var cargoShip = new CargoShip();
        System.out.println(cargoShip.topOfTheStacks_part2(commands, cargoShip.getInput()));
    }

    // for sake of simplicity I omit parsing the input - instead of just creating stacks manually
    public String topOfTheStacks_part1(List<String> commands) {
        List[] lists = getInput();

        // INIT AND FILL STACKS
        var stacks = new Stack[9];
        for (int i = 0; i < 9; i++) {
            stacks[i] = new Stack<Character>();
            lists[i].stream()
                    .forEach(stacks[i]::push);
        }

        for (String s : commands) {
            String[] split = s.replaceAll("[^\\d]", " ").trim().replaceAll(" +", " ").split(" ");
            int count = Integer.parseInt(split[0]);
            int from = Integer.parseInt(split[1]) - 1;
            int to = Integer.parseInt(split[2]) - 1;

            for (int i = 0; i < count; i++) {
                Object item = stacks[from].pop();
                stacks[to].push(item);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            sb.append(stacks[i].pop());
        }

        return sb.toString();
    }

    public String topOfTheStacks_part2(List<String> commands, List[] input) {
        // INIT STACKS
        var stacks = new Stack[9];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<Character>();
            input[i].stream().forEach(stacks[i]::push);
        }

        for (String s : commands) {
            String[] split = s.replaceAll("[^\\d]", " ").trim().replaceAll(" +", " ").split(" ");
            int count = Integer.parseInt(split[0]);
            int from = Integer.parseInt(split[1]) - 1;
            int to = Integer.parseInt(split[2]) - 1;

            Stack tmp = new Stack<>();
            for (int i = 0; i < count; i++) {
                tmp.push(stacks[from].pop());
            }

            for (int i = 0; i < count; i++) {
                stacks[to].push(tmp.pop());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stacks.length; i++) {
            sb.append(stacks[i].peek());
        }

        return sb.toString();
    }

    private List[] getInput() {
        var lists = new List[9];
        lists[0] = List.of("N", "B", "D", "T", "V", "G", "Z", "J");
        lists[1] = List.of("S", "R", "M", "D", "W", "P", "F");
        lists[2] = List.of("V", "C", "R", "S", "Z");
        lists[3] = List.of("R", "T", "J", "Z", "P", "H", "G");
        lists[4] = List.of("T", "C", "J", "N", "D", "Z", "Q", "F");
        lists[5] = List.of("N", "V", "P", "W", "G", "S", "F", "M");
        lists[6] = List.of("G", "C", "V", "B", "P", "Q");
        lists[7] = List.of("Z", "B", "P", "N");
        lists[8] = List.of("W", "P", "J");
        return lists;
    }

    private List[] getSimple() {
        var lists = new List[3];
        lists[0] = List.of("Z", "N");
        lists[1] = List.of("M", "C", "D");
        lists[2] = List.of("P");
        return lists;
    }
}

/*

[J]             [F] [M]
[Z] [F]     [G] [Q] [F]
[G] [P]     [H] [Z] [S] [Q]
[V] [W] [Z] [P] [D] [G] [P]
[T] [D] [S] [Z] [N] [W] [B] [N]
[D] [M] [R] [J] [J] [P] [V] [P] [J]
[B] [R] [C] [T] [C] [V] [C] [B] [P]
[N] [S] [V] [R] [T] [N] [G] [Z] [W]
 1   2   3   4   5   6   7   8   9
 */