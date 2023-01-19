package oop.ex6.scopes;

public class Scope {
    static private int currentScope = 0;

    static public void incrementScope() {
        currentScope++;
    }

    static public void decrementScope() {
        currentScope--;
    }

    static public int getCurrentScope() {
        return currentScope;
    }
}
