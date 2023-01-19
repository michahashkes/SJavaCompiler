package oop.ex6.handlers.variables;

import oop.ex6.main.FileFormatException;

public class IllegalFinalVariableException extends FileFormatException {
    public IllegalFinalVariableException() {
        super("Illegal Final Variable Exception");
    }
}
