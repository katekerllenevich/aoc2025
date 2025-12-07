package dev.kerllenevich.aoc2025.days;

import dev.kerllenevich.aoc2025.AbstractDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day6 extends AbstractDay {

    public Day6() {
        super(6);
    }

    private static class Operation {
        OperationType type = null;
        ArrayList<Long> nums =  new ArrayList<>();
    }

    private enum OperationType {
        Multiply,
        Add
    }

    @Override
    protected String part1() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream()))) {
            ArrayList<Operation> operations = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (int i = 0; i < parts.length; i++) {
                    Operation operation;
                    try {
                        operation = operations.get(i);
                    } catch (IndexOutOfBoundsException e) {
                        operation = new Operation();
                        operations.add(operation);
                    }

                    String part = parts[i];
                    if (part.equals("*")) {
                        operation.type = OperationType.Multiply;
                    } else if (part.equals("+")) {
                        operation.type = OperationType.Add;
                    } else {
                        operation.nums.add(Long.parseLong(part));
                    }
                }
            }

            return String.valueOf(operationSum(operations));
        }
    }

    @Override
    protected String part2() throws IOException {
        List<String> lines = string().lines().toList();
        char[][] matrix = new char[lines.getFirst().length()][lines.size()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                matrix[x][y] = line.charAt(x);
            }
        }

        List<Operation> operations = new ArrayList<>();
        Operation current = null;

        for (char[] line : matrix) {
            char op = line[line.length - 1];
            if (op == '*' || op == '+') {
                if (current != null) operations.add(current);

                current = new Operation();
                current.type = op == '*' ? OperationType.Multiply : OperationType.Add;
            }

            StringBuilder num = new StringBuilder();
            for (int y = 0; y < line.length - 1; y++) {
                char pos = line[y];
                if (pos != ' ') {
                    num.append(pos);
                }
            }

            if (current != null && !num.isEmpty()) {
                current.nums.add(Long.parseLong(num.toString()));
            }
        }

        if (current != null) {
            operations.add(current);
        }

        return String.valueOf(operationSum(operations));
    }

    private static long operationSum(List<Operation> operations) {
        long total = 0;
        for (Operation operation : operations) {
            if (operation.type == OperationType.Add) {
                long sum = 0;
                for (Long num : operation.nums) {
                    sum += num;
                }
                total += sum;
            } else {
                long temp = 1;
                for (Long num : operation.nums) {
                    temp *= num;
                }
                total += temp;
            }
        }
        return total;
    }
}
