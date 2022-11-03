package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);
        // IWorldMap map = new RectangularMap(10, 5);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        out.println(map);
        engine.run();
    }
}
