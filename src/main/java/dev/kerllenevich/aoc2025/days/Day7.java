package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day7 extends AbstractDay {

    public Day7() {
        super(7);
    }

    @Override
    protected String part1() throws IOException {
        List<String> lines = string().lines().toList();
        char[][] matrix = new char[lines.size()][lines.getFirst().length()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            matrix[y] = line.toCharArray();
        }

        boolean[] beams = new boolean[matrix[0].length];
        int splits = 0;

        for (char[] line : matrix) {
            for (int x = 0; x < line.length; x++) {
                char ch = line[x];

                switch (ch) {
                    case 'S' -> beams[x] = true;
                    case '^' -> {
                        if (!beams[x]) {
                            break;
                        }

                        beams[x] = false;
                        if (x != 0) {
                            beams[x - 1] = true;
                        }
                        if (x != line.length - 1) {
                            beams[x + 1] = true;
                        }
                        splits++;
                    }
                }
            }
        }

        return String.valueOf(splits);
    }

    @Override
    protected String part2() throws IOException {
        List<String> lines = string().lines().toList();
        char[][] matrix = new char[lines.size()][lines.getFirst().length()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            matrix[y] = line.toCharArray();
        }

        HashMap<Integer, Long> beams = new HashMap<>();

        for (char[] line : matrix) {
            for (int x = 0; x < line.length; x++) {
                char ch = line[x];

                switch (ch) {
                    case 'S' -> beams.put(x, 1L);
                    case '^' -> {
                        // splitting logic
                        long currBeams = beams.get(x);

                        if (x != 0) {
                            beams.put(x - 1, beams.getOrDefault(x - 1, 0L) + currBeams);
                        }

                        if (x != line.length - 1) {
                            beams.put(x + 1, beams.getOrDefault(x +1, 0L) + currBeams);
                        }

                        beams.put(x, 0L);
                    }
                }
            }
        }

        long sum = 0;
        for (Long value : beams.values()) {
            sum += value;
        }

        return String.valueOf(sum);
    }
}
