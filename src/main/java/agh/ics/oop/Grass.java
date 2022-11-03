package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "*";
    }
}
