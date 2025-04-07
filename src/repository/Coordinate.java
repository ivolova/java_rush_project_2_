package repository;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(final Object o) {

        final Coordinate other = (Coordinate) o;
        if (this.getX() != other.getX()) return false;
        if (this.getY() != other.getY()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Coordinate;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getX();
        result = result * PRIME + this.getY();
        return result;
    }
}
