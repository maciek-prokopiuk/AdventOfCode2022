package pl.mcprok.day1;

import java.util.List;
import java.util.PriorityQueue;

public class MaxSum {

    public int maxSum(List<String> input){
        var maxSoFar = -1;
        var currSum = 0;
        for (int i = 0; i < input.size(); i++) {
            if(input.get(i).isBlank()) {
                if(maxSoFar < currSum) {
                    maxSoFar = currSum;
                }
                currSum = 0;
            } else {
                currSum+= Integer.parseInt(input.get(i));
            }
        }
        return Math.max(maxSoFar, currSum);
    }

    public int maxSumOf3TopElements(List<String> input) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        var currSum = 0;

        for (String s : input) {
            if (s.isBlank()) {
                queue.add(currSum);
                currSum = 0;

                if (queue.size() > 3) {
                    queue.poll();
                }
            } else {
                currSum += Integer.parseInt(s);
            }
        }
        queue.add(currSum);

        queue.poll();
        var sumOfTop3 = 0;
        for(Integer i : queue) {
            sumOfTop3 += i;
        }

        return sumOfTop3;
    }

}
