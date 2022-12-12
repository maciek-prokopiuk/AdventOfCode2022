package pl.mcprok.day11;

import java.util.Deque;
import java.util.Objects;
import java.util.function.Function;


public class Monkey {
    private int opCounter = 0;
    private final Deque<Long> items;
    private final Function<Long, Long> newWorryLevel;

    public int getDivider() {
        return divider;
    }

    private final int divider;
    private final int trueValue;
    private final int falseValue;

    Monkey(Deque<Long> items, Function<Long, Long> newWorryLevel, int divider, int trueValue, int falseValue) {
        this.items = items;
        this.newWorryLevel = newWorryLevel;
        this.divider = divider;
        this.trueValue = trueValue;
        this.falseValue = falseValue;
    }

    public long newWorryLevel(long old) {
        opCounter++;
        return (int) Math.floor(newWorryLevel.apply(old) / 3);
    }

    public boolean test(long worryLevel) {
        return worryLevel % divider == 0;
    }

    public int getTrueValue() {
        return trueValue;
    }

    public int getFalseValue() {
        return falseValue;
    }

    public Deque<Long> getItems() {
        return items;
    }

    public int getOpCounter() {
        return opCounter;
    }

    public long newWorryLevelPart2(Long old, int modulo) {
        opCounter++;
        Long apply = newWorryLevel.apply(old % modulo);
        return apply;
    }
}

