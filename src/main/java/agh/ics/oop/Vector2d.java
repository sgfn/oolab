package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y ,other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y ,other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Vector2d)) {
            return false;
        }
        Vector2d that = (Vector2d) other;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

    public int compareAlongXaxis(Vector2d other) {
        return this.x - other.x;
    }

    public int compareAlongYaxis(Vector2d other) {
        return this.y - other.y;
    }
}
