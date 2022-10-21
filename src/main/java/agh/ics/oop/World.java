package agh.ics.oop;

import static java.lang.System.out;

public class World {
    private static void runAnimal(Animal animal, MoveDirection[] args) {
        runAnimal(animal, args, 0);
    }

    private static void runAnimal(Animal animal, MoveDirection[] args, int verbosity) {
        if (verbosity > 0) {
            out.println(String.format("Start: %s", animal));
        }

        int step = 0;
        for (MoveDirection arg : args) {
            animal.move(arg);
            if (verbosity > 1) {
                out.println(String.format("Krok %2d: %s", ++step, animal));
            }
        }

        if (verbosity > 0 && args.length > 0) {
            out.println(String.format("Koniec: %s", animal));
        }
    }

    public static void main(String[] args) {
        out.println("System wystartował");
        MoveDirection[] mvDirs = OptionsParser.parse(args);
        Animal animal = new Animal();
        runAnimal(animal, mvDirs, 1); // set verbosity=2 to get animal telemetry for each step
        out.println("System zakończył działanie");
    }
}

/*
10. Odpowiedz na pytanie: jak zaimplementować mechanizm, który wyklucza pojawienie się
    dwóch zwierząt w tym samym miejscu.

    Mój pomysł zakłada użycie kontenera typu set z aktualnymi pozycjami zwierząt,
    przy każdym ruchu zwierząt byłby tenże set aktualizowany (zamortyzowane O(1)),
    i jeżeli okazałoby się, że miejsce, do którego zmierza zwierzę, jest już zajęte,
    tzn. w secie istnieje dana pozycja (O(1)), ruch nie zostałby wykonany.
*/
