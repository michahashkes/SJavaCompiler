package oop.ex6.handlers.variables;

import oop.ex6.main.FileFormatException;

public class IllegalInitializedVariableException extends FileFormatException {
    public IllegalInitializedVariableException() {
        super("Illegal Initialized Variable Exception");
    }
}
