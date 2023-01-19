package oop.ex6.handlers.variables;

import oop.ex6.main.FileFormatException;

public class IllegalAssignException extends FileFormatException {
    public IllegalAssignException() {
        super("Illegal Assign Exception");
    }
}
