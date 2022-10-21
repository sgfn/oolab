package agh.ics.oop;

import java.util.Vector;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        Vector<MoveDirection> v = new Vector<MoveDirection>();
        for (String arg : args) {
            if (arg.equals("forward") || arg.equals("f")) {
                v.add(MoveDirection.FORWARD);
            } else if (arg.equals("backward") || arg.equals("b")) {
                v.add(MoveDirection.BACKWARD);
            } else if (arg.equals("right") || arg.equals("r")) {
                v.add(MoveDirection.RIGHT);
            } else if (arg.equals("left") || arg.equals("l")) {
                v.add(MoveDirection.LEFT);
            }
        }

        return v.toArray(new MoveDirection[v.size()]);
    }
}
