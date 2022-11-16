package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final MapVisualizer mapVis = new MapVisualizer(this);
    protected final MapBoundary mapBoundary = new MapBoundary();

    protected Map<Vector2d, IMapElement> entities = new HashMap<Vector2d, IMapElement>();
    protected Map<Vector2d, IMapElement> incorporealEntities = new HashMap<Vector2d, IMapElement>();

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
            entities.put(animal.getPosition(), animal);
            animal.addObserver(this);
            mapBoundary.placeElem(animal);
            animal.addObserver(mapBoundary);
            return true;
        }
        throw new IllegalArgumentException(String.format("Unable to place animal at position %s", animal.getPosition()));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return entities.containsKey(position) || incorporealEntities.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object o = entities.get(position);
        if (o != null) {
            return o;
        }
        return incorporealEntities.get(position);
    }

    public abstract Vector2d[] getBounds();

    @Override
    public String toString() {
        Vector2d[] bounds = getBounds();
        return mapVis.draw(bounds[0], bounds[1]);
    }

    protected abstract void notifyRemovalOfIncorporealEntities(int amount);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement entity = entities.remove(oldPosition);
        if (entity != null) {
            entities.put(newPosition, entity);
            Object o = incorporealEntities.get(newPosition);
            if (o != null) {
                incorporealEntities.remove(newPosition);
                notifyRemovalOfIncorporealEntities(1);
            }
        }
    }
}
