package oop.ex6.handlers.scopes;

import oop.ex6.main.FileFormatException;

public class IllegalMethodDefinitionException extends FileFormatException {
    public IllegalMethodDefinitionException() {
        super("Illegal Method Definition Exception");
    }
}
