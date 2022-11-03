package agh.ics.oop;

import java.util.Vector;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final MapVisualizer mapVis = new MapVisualizer(this);

    protected Vector<IMapElement> entities = new Vector<IMapElement>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object o = objectAt(position);
        if (o != null) {
            return !(o instanceof Animal);
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            entities.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (IMapElement entity : entities) {
            if (entity.isAt(position)) {
                return entity;
            }
        }
        return null;
    }

    protected abstract Vector2d[] getBounds();

    @Override
    public String toString() {
        Vector2d[] bounds = getBounds();
        return mapVis.draw(bounds[0], bounds[1]);
    }
}
