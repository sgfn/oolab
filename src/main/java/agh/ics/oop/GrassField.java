package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap {
    public GrassField(int n) {
        final int grassGenUpperBound = (int)(Math.sqrt((double)(n*10)))+1;
        Random rng = new Random();
        for (int i=0; i<n; ++i) {
            Vector2d pos;
            do {
                // Jest szansa, że ta pętla nigdy się nie zakończy. Szansa nikła, ale niezerowa. Too bad!
                pos = new Vector2d(rng.nextInt(grassGenUpperBound), rng.nextInt(grassGenUpperBound));
            } while (!canMoveTo(pos));
            entities.add(new Grass(pos));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object obj = null;
        for (IMapElement entity : entities) {
            if (entity.isAt(position)) {
                if (obj == null || entity instanceof Animal) {
                    obj = entity;
                }
            }
        }
        return obj;
    }

    @Override
    protected Vector2d[] getBounds() {
        Vector2d boundaryLowerLeft = new Vector2d(0, 0);
        Vector2d boundaryUpperRight = new Vector2d(0, 0);
        for (IMapElement entity : entities) {
            boundaryLowerLeft = boundaryLowerLeft.lowerLeft(entity.getPosition());
            boundaryUpperRight = boundaryUpperRight.upperRight(entity.getPosition());
        }
        return new Vector2d[] { boundaryLowerLeft, boundaryUpperRight };
    }
}
