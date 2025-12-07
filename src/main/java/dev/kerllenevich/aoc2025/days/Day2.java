package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.IOException;

public class Day2 extends AbstractDay {

    public Day2() {
        super(2);
    }

    @Override
    protected String part1() throws IOException {
        long sum = 0;

        for (String ranges : string().split(",")) {
            long low = Long.parseLong(ranges.split("-")[0]);
            long high = Long.parseLong(ranges.split("-")[1]);

            for (long i = low; i <= high; i++) {
                long digits = (long) Math.log10(i) + 1;
                if (digits % 2 != 0) {
                    continue;
                }

                long divider = (long) Math.pow(10, digits / 2);
                if (i / divider == i % divider) {
                    sum += i;
                }
            }
        }

        return String.valueOf(sum);
    }

    @Override
    protected String part2() throws IOException {
        long sum = 0;

        for (String ranges : string().split(",")) {
            long low = Long.parseLong(ranges.split("-")[0]);
            long high = Long.parseLong(ranges.split("-")[1]);

            for (long i = low; i <= high; i++) {
               String string = String.valueOf(i);
               if (string.matches("^(.+)\\1+$")) {
                   sum += i;
               }
            }
        }

        return String.valueOf(sum);
    }
}

