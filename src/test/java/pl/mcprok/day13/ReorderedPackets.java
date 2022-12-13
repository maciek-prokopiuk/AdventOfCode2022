package pl.mcprok.day13;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ReorderedPackets extends Base {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void part1() throws IOException, URISyntaxException {
        var sum = 0;
        var counter = 1;
        List<String> input = getLinesFromInput("day13/input.txt");
        for(var i = 0;i<input.size();) {
            String line1 = input.get(i++);
            String line2 = input.get(i++);
            if(compare(mapper.readTree(line1), mapper.readTree(line2)) == 1) {
                sum += counter;
            }
            i++;
            counter++;
        }

        System.out.println(sum);
    }

    @Test
    public void part2() throws Exception {
        List<String> input = getLinesFromInput("day13/input.txt");
        input.add("[[2]]");
        input.add("[[6]]");

        List<JsonNode> sorted = input.stream()
                .filter(s -> !s.isBlank())
                .map(content -> {
                    try {
                        return mapper.readTree(content);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return mapper.createObjectNode();
                })

                .sorted(ReorderedPackets::compare)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(sorted);

        JsonNode divider1 = mapper.readTree("[[2]]");
        JsonNode divider2 = mapper.readTree("[[6]]");

        int res = 1;
        for (int i = 0; i < sorted.size(); i++) {
            if(sorted.get(i).equals(divider1) || sorted.get(i).equals(divider2)) {
                res *= i+1;
            }
        }
        System.out.println(res);
    }

    // comparator based on the task content can be used for both part1 and 2
    public static int compare(JsonNode left, JsonNode right) {
        if (left instanceof IntNode l && right instanceof IntNode r) {
            return Integer.compare(r.asInt(), l.asInt());
        }

        if (left instanceof ArrayNode l && right instanceof IntNode r) {
            return compare(l, mapper.createArrayNode().add(r.asInt()));
        }
        if (left instanceof IntNode l && right instanceof ArrayNode r) {
            return compare(mapper.createArrayNode().add(l.asInt()), r);
        }

        var compareIndex = 0;

        if (left instanceof ArrayNode l && right instanceof ArrayNode) {
            while (compareIndex < left.size() || compareIndex < right.size()) {
                if(compareIndex == left.size()) {
                    return 1;
                }

                if(compareIndex == right.size()) {
                    return -1;
                }

                int c = compare(l.get(compareIndex), right.get(compareIndex));
                if (c == 0) {
                    compareIndex++;
                } else return c;
            }
        }

        return 0;
    }

}
