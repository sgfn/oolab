package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private final int grassGenUpperBound;
    private final Random rng = new Random();

    private void generateGrass() {
        Vector2d pos;
            do {
                // Jest szansa, że ta pętla nigdy się nie zakończy. Szansa nikła, ale niezerowa. Too bad!
                pos = new Vector2d(rng.nextInt(grassGenUpperBound), rng.nextInt(grassGenUpperBound));
            } while (isOccupied(pos));
            incorporealEntities.put(pos, new Grass(pos));
    }

    public GrassField(int n) {
        grassGenUpperBound = (int)(Math.sqrt((double)(n*10)))+1;
        for (int i=0; i<n; ++i) {
            generateGrass();
        }
    }

    @Override
    protected Vector2d[] getBounds() {
        Vector2d boundaryLowerLeft = new Vector2d(0, 0);
        Vector2d boundaryUpperRight = new Vector2d(0, 0);
        for (Vector2d pos : entities.keySet()) {
            boundaryLowerLeft = boundaryLowerLeft.lowerLeft(pos);
            boundaryUpperRight = boundaryUpperRight.upperRight(pos);
        }
        for (Vector2d pos : incorporealEntities.keySet()) {
            boundaryLowerLeft = boundaryLowerLeft.lowerLeft(pos);
            boundaryUpperRight = boundaryUpperRight.upperRight(pos);
        }
        return new Vector2d[] { boundaryLowerLeft, boundaryUpperRight };
    }

    // efektem ubocznym dzisiejszych labów jest to, że istoty cielesne zaczęły zjadać trawę
    @Override
    protected void notifyRemovalOfIncorporealEntities(int amount) {
        for (int i=0; i<amount; ++i) {
            generateGrass();
        }
    };
}
