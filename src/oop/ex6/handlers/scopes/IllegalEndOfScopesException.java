package oop.ex6.handlers.scopes;

import oop.ex6.main.FileFormatException;

public class IllegalEndOfScopesException extends FileFormatException {
    public IllegalEndOfScopesException() {
        super("Illegal End Of Scopes Exception");
    }
}
