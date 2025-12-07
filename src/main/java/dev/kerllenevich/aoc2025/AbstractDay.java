package dev.kerllenevich.aoc2025;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class AbstractDay {
    private final int day;
    private final String filename;

    private AbstractDay() {
        day = -1;
        filename = null;
    }

    protected AbstractDay(int day) {
        this.day = day;
        filename = "/day" + day + ".txt";

        if (getClass().getResource(filename) == null) {
            throw new IllegalArgumentException(filename + " was not found.");
        }
    }

    protected final InputStream inputStream() {
        return getClass().getResourceAsStream(filename);
    }

    protected final String string() throws IOException {
        try (InputStream stream = inputStream()) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    protected abstract String part1() throws IOException;
    protected abstract String part2() throws IOException;

    @Override
    public String toString() {
        return "AbstractDay[" + day + "]";
    }
}
