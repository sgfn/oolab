package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class IntegrationTest {
    private static class AnimalMvmtTestData {
        final String[] args;
        final MoveDirection[] moves;
        final Vector2d finalPosition;
        final MapDirection finalOrientation;
        AnimalMvmtTestData(String[] a, MoveDirection[] m, Vector2d p, MapDirection o) {
            args = a; moves = m; finalPosition = p; finalOrientation = o;
        }
    }

    @Test
    void testAnimalMovement() {
        final AnimalMvmtTestData[] testData = {
            new AnimalMvmtTestData(
                new String[] {
                    "r", "r", "f", "l", "b", "b", "r", "f"
                },
                new MoveDirection[] {
                    MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT,
                    MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD
                },
                new Vector2d(0, 0),
                MapDirection.SOUTH
            ),
            new AnimalMvmtTestData(
                new String[] {
                    "right", "right", "forward", "left", "backward", "backward", "right", "forward"
                },
                new MoveDirection[] {
                    MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT,
                    MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD
                },
                new Vector2d(0, 0),
                MapDirection.SOUTH
            ),
            new AnimalMvmtTestData(
                new String[] {
                    "right", "forward", "f", "forward"
                },
                new MoveDirection[] {
                    MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD
                },
                new Vector2d(4, 2),
                MapDirection.EAST
            ),
            new AnimalMvmtTestData(
                new String[] {
                    "f", "f", "f", "b", "f", "f", "r", "r", "b", "b"
                },
                new MoveDirection[] {
                    MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                    MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                    MoveDirection.BACKWARD, MoveDirection.BACKWARD
                },
                new Vector2d(2, 4),
                MapDirection.SOUTH
            ),
            new AnimalMvmtTestData(
                new String[] {
                    "r", "b", "b", "l", "f", "f", "f", "f", "r", "b", "b", "r", "b", "b", "r", "f", "f", "b"
                },
                new MoveDirection[] {
                    MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT,
                    MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                    MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT,
                    MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                    MoveDirection.FORWARD, MoveDirection.BACKWARD
                },
                new Vector2d(1, 4),
                MapDirection.WEST
            ),
            new AnimalMvmtTestData(
                new String[] {
                    "right", "roight", "letf", "przód", "x", "forward", "q", "AAAAAAAAAAA", "", "l"
                },
                new MoveDirection[] {
                    MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT
                },
                new Vector2d(3, 2),
                MapDirection.NORTH
            )
        };

        for (AnimalMvmtTestData td : testData) {
            Animal animal = new Animal();
            MoveDirection[] moves = OptionsParser.parse(td.args);
            assertTrue(Arrays.equals(moves, td.moves));
            for (var mv : moves) {
                animal.move(mv);
            }
            assertTrue(animal.isAt(td.finalPosition));
            assertTrue(animal.isFacing(td.finalOrientation));
        }
    }
}
