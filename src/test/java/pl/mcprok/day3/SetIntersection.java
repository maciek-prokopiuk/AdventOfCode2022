package pl.mcprok.day3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetIntersection {

    public int findPriorityOfSetIntersection(String s) {
        var s1 = prepareSet(s.substring(0,s.length()/2));
        var s2 = prepareSet(s.substring(s.length()/2));

        s2.retainAll(s1);

        return s2.stream()
                .findFirst()
                .map(this::findPriority)
                .orElseThrow();
    }

    public int findPriorityOf3Sets(String s1, String s2, String s3) {
        var set1 = prepareSet(s1);
        var set2 = prepareSet(s2);
        var set3 = prepareSet(s3);

        set1.retainAll(set2); // common in 1 and 2
        set3.retainAll(set1); // common in all 3

        return set3.stream()
                .findFirst()
                .map(this::findPriority)
                .orElseThrow();
    }

    private Set<Character> prepareSet(String s) {
        var result = new HashSet<Character>();
        char[] chars = s.toCharArray();
        for(char aChar : chars) {
            result.add(aChar);
        }
        return result;
    }

    private int findPriority(Character c) {
        if(Character.isUpperCase(c)) {
            return c - 38;
        } else {
            return c-96;
        }
    }
}
