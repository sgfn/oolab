package agh.ics.oop;

import java.util.Vector;

public class Animal extends AbstractWorldMapElement {
    private MapDirection facing = MapDirection.NORTH;

    private final IWorldMap map;
    private Vector<IPositionChangeObserver> observers = new Vector<IPositionChangeObserver>();
    
    public Animal(IWorldMap map) {
        this.map = map;
        this.pos = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.pos = initialPosition;
    }

    @Override
    public String toString() {
        return switch (facing) {
            case NORTH -> "Λ";
            case SOUTH -> "V";
            case EAST -> ">";
            case WEST -> "<";
        };
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
                    positionChanged(pos, pos.add(move));
                    pos = pos.add(move);
                }
            } else {
                if (map.canMoveTo(pos.subtract(move))) {
                    positionChanged(pos, pos.subtract(move));
                    pos = pos.subtract(move);
                }
            }
            break;
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        for (IPositionChangeObserver o : observers) {
            if (o == observer) {
                observers.remove(o);
            }
        }
    }

    private void positionChanged(Vector2d oldPos, Vector2d newPos) {
        for (IPositionChangeObserver o : observers) {
            o.positionChanged(oldPos, newPos);
        }
    }
}
