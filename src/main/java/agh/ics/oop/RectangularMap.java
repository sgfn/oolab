package agh.ics.oop;

import java.util.Vector;

public class RectangularMap implements IWorldMap {
    private final Vector2d boundaryLowerLeft;
    private final Vector2d boundaryUpperRight;
    private final MapVisualizer mapVis = new MapVisualizer(this);

    private Vector<Animal> animals = new Vector<Animal>();

    public RectangularMap(int width, int height) {
        // Przyjmuję, że mapa ma swój lewy dolny róg w (0, 0)
        boundaryLowerLeft  = new Vector2d(0, 0);
        boundaryUpperRight = new Vector2d(width-1, height-1);
    }

    public boolean canMoveTo(Vector2d position) {
        return boundaryLowerLeft.precedes(position) &&
               boundaryUpperRight.follows(position) &&
               !isOccupied(position);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPos())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        // Osobiście wolałbym tu użyć seta/mapy - lepsza złożoność byłaby,
        // gdyby interfejs IWorldMap umożliwiał przekazywanie do mapy informacji,
        // że dane zwierzę się ruszyło, np. jakieś void registerMove(Animal a)
        // albo void registerFromTo(Vector2d from, Vector2d to)...
        for (Animal a : animals) {
            if (a.isAt(position)) {
                return a;
            }
        }
        return null;
    }

    public String toString() {
        return mapVis.draw(boundaryLowerLeft, boundaryUpperRight);
    }
}
