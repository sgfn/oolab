package agh.ics.oop;

public class Animal {
    private static final Vector2d boundaryLowerLeft = new Vector2d(0, 0);
    private static final Vector2d boundaryUpperRight = new Vector2d(4, 4);

    private MapDirection facing = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2, 2);

    public String toString() {
        return String.format("Pozycja %s, orientacja %s", pos.toString(), facing.toString());
    }

    public boolean isAt(Vector2d position) {
        return pos.equals(position);
    }

    public boolean isFacing(MapDirection direction) {
        return facing.equals(direction);
    }

    private boolean isInBounds(Vector2d position) {
        return boundaryLowerLeft.precedes(position) &&
               boundaryUpperRight.follows(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
        case RIGHT:
            facing = facing.next();
            break;
        case LEFT:
            facing = facing.previous();
            break;
        default:
            Vector2d move = facing.toUnitVector();
            if (direction == MoveDirection.FORWARD) {
                if (isInBounds(pos.add(move))) {
                    pos = pos.add(move);
                }
            } else {
                if (isInBounds(pos.subtract(move))) {
                    pos = pos.subtract(move);
                }
            }
            break;
        }
    }
}
