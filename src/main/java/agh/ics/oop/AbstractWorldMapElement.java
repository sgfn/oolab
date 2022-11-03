package agh.ics.oop;

public abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d pos;

    @Override
    public final Vector2d getPosition() {
        return pos;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return pos.equals(position);
    }
}
