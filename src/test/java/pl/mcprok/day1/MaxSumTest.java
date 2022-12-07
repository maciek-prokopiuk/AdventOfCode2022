package pl.mcprok.day1;


import org.junit.jupiter.api.Test;
import pl.mcprok.utils.Base;

class MaxSumTest extends Base {

    @Test
    void maxSum() throws Exception {
        System.out.println(new MaxSum().maxSum(getLinesFromInput("day1/input.txt")));
    }

    @Test
    void sumOfTop3() throws Exception {
        System.out.println(new MaxSum().maxSumOf3TopElements(getLinesFromInput("day1/input.txt")));
    }
}