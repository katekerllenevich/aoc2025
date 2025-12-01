package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1 extends AbstractDay {

    public Day1() {
        super(1);
    }

    @Override
    protected String part1() throws IOException {
        int pos = 50;
        int zeroCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == 'L') {
                    pos -= Integer.valueOf(line.substring(1));
                } else if (line.charAt(0) == 'R') {
                    pos += Integer.valueOf(line.substring(1));
                }

                pos = ((pos % 100) + 100) % 100;
                if (pos == 0) {
                    zeroCount++;
                }
            }
        }

        return String.valueOf(zeroCount);
    }

    @Override
    protected String part2() throws IOException {
        int pos = 50;
        int zeroCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean left = line.charAt(0) == 'L';

                for (int times = Integer.valueOf(line.substring(1)); times > 0; times--) {
                    pos += left ? -1 : 1;
                    pos = ((pos % 100) + 100) % 100;

                    if (pos == 0) {
                        zeroCount++;
                    }
                }
            }
        }

        return String.valueOf(zeroCount);
    }
}
