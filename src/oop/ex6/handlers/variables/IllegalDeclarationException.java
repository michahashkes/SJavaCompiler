package oop.ex6.handlers.variables;

import oop.ex6.main.FileFormatException;

public class IllegalDeclarationException extends FileFormatException {
    public IllegalDeclarationException() {
        super("Illegal Declaration Exception()");
    }
}
