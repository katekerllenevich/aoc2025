package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Day5 extends AbstractDay {

    public Day5() {
        super(5);
    }

    @Override
    protected String part1() throws IOException {
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            HashMap<Long, Long> ranges = new HashMap<>();

            String line;
            boolean section1 = true;

            while ((line = reader.readLine()) != null) {
                if (section1) {
                    if (line.isEmpty()) {
                        section1 = false;
                        continue;
                    }

                    String[] range = line.split("-");
                    long start = Long.parseLong(range[0]);
                    long end = Long.parseLong(range[1]);

                    ranges.put(start, Math.max(ranges.getOrDefault(start, end), end));
                } else {
                    long num = Long.parseLong(line);

                    for (long start : ranges.keySet()) {
                        long end = ranges.get(start);

                        if (start <= num && end >= num) {
                            total++;
                            break;
                        }
                    }
                }
            }
        }

        return String.valueOf(total);
    }

    @Override
    protected String part2() throws IOException {
        throw new UnsupportedOperationException(); // todo
    }
}
