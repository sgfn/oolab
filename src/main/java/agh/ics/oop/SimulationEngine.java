package agh.ics.oop;

import java.util.Vector;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] mvDirs;
    private final IWorldMap map;
    private final Animal[] animals;

    public SimulationEngine(MoveDirection[] mvDirs, IWorldMap map, Vector2d[] initialPositions) {
        this.map = map;
        this.mvDirs = mvDirs;

        Vector<Animal> v = new Vector<Animal>();
        for (Vector2d pos : initialPositions) {
            Animal a = new Animal(this.map, pos);
            if (this.map.place(a)) {
                v.add(a);
            }
        }
        this.animals = v.toArray(new Animal[v.size()]);
    }

    @Override
    public void run() {
        int animIndex = 0;
        for (MoveDirection mvDir : mvDirs) {
            animals[animIndex++].move(mvDir);
            System.out.println(String.format("ANIMAL %d: %s\n%s", animIndex, mvDir, map));
            if (animIndex == animals.length) {
                animIndex = 0;
            }
        }
    }
}
