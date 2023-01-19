package oop.ex6.handlers.scopes;

import oop.ex6.main.FileFormatException;

public class IllegalMethodCallException extends FileFormatException {
    public IllegalMethodCallException() {
        super("Illegal Method Call Exception");
    }
}
