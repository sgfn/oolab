package agh.ics.oop;

import static java.lang.System.out;

public class World {
    private static MoveDirection[] argsToDirs(String[] args) {
        MoveDirection[] dirs = new MoveDirection[args.length];
        for (int i = 0; i < args.length; ++i) {
            dirs[i] = switch(args[i]) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
        }
        return dirs;
    }

    private static void run(MoveDirection[] args) {
        for (MoveDirection arg : args) {
            if (arg == MoveDirection.FORWARD) {
                out.println("Zwierzę zmierza w przód");
            } else if (arg == MoveDirection.BACKWARD) {
                out.println("Zwierzę zmierza w tył");
            } else if (arg == MoveDirection.RIGHT) {
                out.println("Zwierzę skręca w prawo");
            } else if (arg == MoveDirection.LEFT) {
                out.println("Zwierzę skręca w lewo");
            }
        }
    }

    public static void main(String[] args) {
        out.println("System wystartował");
        MoveDirection[] dirs = argsToDirs(args);
        run(dirs);
        out.println("System zakończył działanie");

        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
}
