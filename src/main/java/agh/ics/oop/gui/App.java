package agh.ics.oop.gui;

import static java.lang.System.out;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import agh.ics.oop.*;

public class App extends Application {
    private AbstractWorldMap map;
    private IEngine engine;

    public void init() {
        try {
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(String[]::new));
            // map = new RectangularMap(10, 5);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2, 2), new Vector2d(3, 4) };
            engine = new SimulationEngine(directions, map, positions);
            out.println(map);
            engine.run();
        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void start(Stage primaryStage) {
        Vector2d[] bounds = map.getBounds();
        Vector2d lowerLeft = bounds[0];
        Vector2d upperRight = bounds[1];
        int dimX = upperRight.x - lowerLeft.x + 1;
        int dimY = upperRight.y - lowerLeft.y + 1;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int i=0; i<=dimX; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(30));
        }
        for (int i=0; i<=dimY; ++i) {
            grid.getRowConstraints().add(new RowConstraints(30));
        }

        grid.add(new Label("y\\x"), 0, 0);

        for (int dx=0; dx<dimX; ++dx) {
            grid.add(new Label(String.format("%d", lowerLeft.x+dx)), dx+1, 0);
        }

        for (int dy=0; dy<dimY; ++dy) {
            grid.add(new Label(String.format("%d", lowerLeft.y+dy)), 0, dimY-dy);
            for (int dx=0; dx<dimX; ++dx) {
                String result = " ";
                Vector2d currentPosition = new Vector2d(lowerLeft.x+dx, lowerLeft.y+dy);
                if (this.map.isOccupied(currentPosition)) {
                    Object object = this.map.objectAt(currentPosition);
                    if (object != null) {
                        result = object.toString();
                    }
                }
                grid.add(new Label(result), dx+1, dimY-dy);
            }
        }

        for (var x : grid.getChildren()) {
            GridPane.setHalignment(x, HPos.CENTER);
        }

        Scene scene = new Scene(grid, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
