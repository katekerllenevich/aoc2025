package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 extends AbstractDay {

    public Day4() {
        super(4);
    }

    @Override
    protected String part1() throws IOException {
        int total = 0;

        List<String> lines = string().lines().toList();
        boolean[][] matrix = new boolean[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                matrix[i][j] = lines.get(i).charAt(j) == '@';
            }
        }

        for (int y = 0; y < matrix.length; y++) {
            boolean[] row = matrix[y];

            for (int x = 0; x < row.length; x++) {
                if (!matrix[y][x]) {
                    continue;
                }

                if (getOccupied(y, x, matrix) < 4) {
                    total++;
                }
            }
        }

        return String.valueOf(total);
    }

    @Override
    protected String part2() throws IOException {
        int total = 0;

        List<String> lines = string().lines().toList();
        boolean[][] matrix = new boolean[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                matrix[i][j] = lines.get(i).charAt(j) == '@';
            }
        }

        int lastRemoved = -1;
        while (lastRemoved != 0) {
            int removed = 0;
            boolean[][] modifiedMatrix = new boolean[matrix.length][matrix[0].length];
            for (int i = 0; i < modifiedMatrix.length; i++) {
                System.arraycopy(matrix[i], 0, modifiedMatrix[i], 0, modifiedMatrix[0].length);
            }

            for (int y = 0; y < matrix.length; y++) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (!matrix[y][x]) {
                        continue;
                    }

                    if (getOccupied(y, x, matrix) < 4) {
                        removed++;
                        modifiedMatrix[y][x] = false;
                    }
                }
            }

            total += removed;
            lastRemoved = removed;

            matrix = modifiedMatrix;
        }

        return String.valueOf(total);
    }

    private static int getOccupied(int y, int x, boolean[][] matrix) {
        int occupied = 0;
        for (int j = y - 1; j <= y + 1; j++) {
            if (j < 0 || j >= matrix.length) {
                continue;
            }

            for (int i = x - 1; i <= x + 1; i++) {
                if (i < 0 || i >= matrix[y].length || (y == j && x == i)) {
                    continue;
                }

                if (matrix[j][i]) {
                    occupied++;
                }
            }
        }
        return occupied;
    }
}
