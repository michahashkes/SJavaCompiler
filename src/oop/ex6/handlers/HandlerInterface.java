package oop.ex6.handlers;

import oop.ex6.handlers.scopes.IllegalEndOfScopesException;
import oop.ex6.handlers.scopes.IllegalIfWhileException;
import oop.ex6.handlers.scopes.IllegalMethodCallException;
import oop.ex6.handlers.scopes.IllegalMethodDefinitionException;
import oop.ex6.handlers.variables.IllegalAssignException;
import oop.ex6.handlers.variables.IllegalDeclarationException;
import oop.ex6.handlers.variables.IllegalFinalVariableException;
import oop.ex6.handlers.variables.IllegalInitializedVariableException;

import java.util.regex.Matcher;

public interface HandlerInterface {
    /**
     *
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */
    public boolean handleLine(Matcher line) throws IllegalDeclarationException, IllegalInitializedVariableException, IllegalFinalVariableException, IllegalAssignException, IllegalEndOfScopesException, IllegalIfWhileException, IllegalMethodCallException, IllegalMethodDefinitionException;
}
