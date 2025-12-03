package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day3 extends AbstractDay {

    public Day3() {
        super(3);
    }

    @Override
    protected String part1() throws IOException {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int first = Character.getNumericValue(line.charAt(0));
                int second = Character.getNumericValue(line.charAt(1));

                for (int i = 1; i < line.length(); i++) {
                    int num = Character.getNumericValue(line.charAt(i));

                    if (i != line.length() - 1 && num > first) {
                        first = num;
                        second = Character.getNumericValue(line.charAt(i + 1));
                    } else if (num > second) {
                        second = num;
                    }
                }

                sum += Integer.parseInt(first + "" + second);
            }
        }

        return String.valueOf(sum);
    }

    @Override
    protected String part2() throws IOException {
        long sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] values = new int[12];
                Arrays.fill(values, 0);

                for (int i = 0; i < line.length(); i++) {
                    // the window analyzes 12 digits of the string at a time
                    // however if there are less than 12 digits in the string
                    // we fill the first ares with zero
                    int[] window = new int[12];

                    int fillZeros = 12 - Math.min(12, line.length() - i);
                    for (int j = 0; j < fillZeros; j++) {
                        window[j] = 0;
                    }
                    for (int j = fillZeros; j < window.length; j++) {
                        window[j] = Character.getNumericValue(line.charAt(i + j - fillZeros));
                    }

                    // now analyze the window
                    for (int j = 0; j < window.length; j++) {
                        // check starting at this point in the window
                        // is the value greater than in the values array
                        int offset = j;

                        for (int k = offset; k < window.length; k++) {
                            if (window[k] > values[k]) {
                                values[k] = window[k];

                                // if it is, then update the rest of the array
                                for (int x = k; x < window.length; x++) {
                                    values[x] = window[x];
                                }

                                break; // next loop
                            }
                        }
                    }
                }

                StringBuilder concat = new StringBuilder();
                for (int value : values) {
                    concat.append(value);
                }
                sum += Long.parseLong(concat.toString());
            }
        }

        return String.valueOf(sum);
    }
}
