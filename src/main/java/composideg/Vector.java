package composideg;

import java.util.Objects;

public final class Vector {
    private final int x;
    private final int y;

    public static Vector up(int y) {
        return new Vector(0, -y);
    }

    public static Vector down(int y) {
        return new Vector(0, y);
    }

    public static Vector left(int x) {
        return new Vector(-x, 0);
    }

    public static Vector right(int x) {
        return new Vector(x, 0);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector move(Vector other) {
        return new Vector(x + other.getX(), y + other.getY());
    }

    public double distanceTo(Vector other) {
        int horiz = Math.abs(this.x - other.getX());
        int vert = Math.abs(this.y - other.getY());

        return Math.hypot(vert, horiz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
