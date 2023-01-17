package oop.ex6.handlers;

import oop.ex6.main.LineTypes;

import java.util.HashSet;

public class LineHandler {
    private final HashSet<LineTypes> declareSet = new HashSet<LineTypes>();
    private final HashSet<LineTypes> assignSet = new HashSet<LineTypes>();

    public static void handleLine(LineTypes key, String line) {
    }
}
