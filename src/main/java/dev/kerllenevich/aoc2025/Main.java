package dev.kerllenevich.aoc2025;

import dev.kerllenevich.aoc2025.days.Day1;
import dev.kerllenevich.aoc2025.days.Day2;
import dev.kerllenevich.aoc2025.days.Day3;
import dev.kerllenevich.aoc2025.days.Day4;
import dev.kerllenevich.aoc2025.days.Day5;

import java.util.Scanner;

public class Main {
    private static final int MAX_DAYS = 12;
    private static final int MAX_PARTS = 2;

    private static final AbstractDay[] days = new AbstractDay[MAX_DAYS];

    public static void main(String[] args) {
        registerDays();

        System.out.printf("""
                Welcome to Advent of Code 2025 by Kate Kerllenevich.
                \s
                Please input which day and part you would like to run!
                This should follow the format <day>/<part>.
                For example, day 7, part 2 would be formatted as `7/2`.
                \s
                This year there are %d days, each has a maximum of %d parts.
                \s
                 >\s
                """, MAX_DAYS, MAX_PARTS);

        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.next();
        ParsedInput input = parse(inputString);

        while (input == null) {
            System.out.printf("""
                    `%s` is not a valid input. Please try again.
                    \s
                     >\s
                    """, inputString);

            inputString = scanner.next();
            input = parse(inputString);
        }

        AbstractDay day = days[input.day - 1];
        if (day == null) {
            System.out.printf("""
                    Day %d is not currently implemented.
                    
                    Did you register the day?
                    """, input.day);
            return;
        }

        try {
            String output = input.part == 1 ? day.part1() : day.part2();
            System.out.printf("""
                    Day %d, part %d produced the following output
                    
                     > %s
                    """, input.day, input.part, output);
        } catch (UnsupportedOperationException e) {
            System.out.printf("""
                    Day %d, part %d is not currently implemented. Check back later!
                    """, input.day, input.part);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerDays() {
        days[0] = new Day1();
        days[1] = new Day2();
        days[2] = new Day3();
        days[3] = new Day4();
        days[4] = new Day5();
    }

    private static ParsedInput parse(String input) {
        String[] parts = input.split("/");
        if (parts.length != 2) {
            return null;
        }

        try {
            int first = Integer.parseInt(parts[0]);
            int second = Integer.parseInt(parts[1]);

            if ((first <= 0 || first > MAX_DAYS) || (second <= 0 || second > MAX_PARTS)) {
                return null;
            }

            return new ParsedInput(first, second);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private record ParsedInput(int day, int part) {
    }
}
