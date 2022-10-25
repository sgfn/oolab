package agh.ics.oop;

public class Animal {
    private MapDirection facing = MapDirection.NORTH;
    private Vector2d pos = new Vector2d(2, 2);

    private final IWorldMap map;
    
    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.pos = initialPosition;
    }

    public String toString() {
        return switch (facing) {
            case NORTH -> "Λ";
            case SOUTH -> "V";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public Vector2d getPos() {
        return pos;
    }

    public boolean isAt(Vector2d position) {
        return pos.equals(position);
    }

    public boolean isFacing(MapDirection direction) {
        return facing.equals(direction);
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
                if (map.canMoveTo(pos.add(move))) {
                    pos = pos.add(move);
                }
            } else {
                if (map.canMoveTo(pos.subtract(move))) {
                    pos = pos.subtract(move);
                }
            }
            break;
        }
    }
}
