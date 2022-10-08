package agh.ics.oop;

import static java.lang.System.out;

public class World {
    private static Direction[] argsToDirs(String[] args) {
        Direction[] dirs = new Direction[args.length];
        for (int i = 0; i < args.length; ++i) {
            dirs[i] = switch(args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            };
        }
        return dirs;
    }

    private static void run(Direction[] args) {
        for (Direction arg : args) {
            if (arg == Direction.FORWARD) {
                out.println("Zwierzę zmierza w przód");
            } else if (arg == Direction.BACKWARD) {
                out.println("Zwierzę zmierza w tył");
            } else if (arg == Direction.RIGHT) {
                out.println("Zwierzę zmierza w prawo");
            } else if (arg == Direction.LEFT) {
                out.println("Zwierzę zmierza w lewo");
            }
        }
    }

    public static void main(String[] args) {
        out.println("System wystartował");
        Direction[] dirs = argsToDirs(args);
        run(dirs);
        out.println("System zakończył działanie");
    }
}
