package oop.ex6.handlers;

import oop.ex6.handlers.scopes.*;
import oop.ex6.handlers.variables.IllegalAssignException;
import oop.ex6.handlers.variables.IllegalDeclarationException;
import oop.ex6.handlers.variables.IllegalFinalVariableException;
import oop.ex6.handlers.variables.IllegalInitializedVariableException;
import oop.ex6.main.regex.StatementTypes;

import java.util.regex.Matcher;


public class LineHandler {

    /**
     * handler with the line in the code
     * @param statementTypes
     * @param line
     * @return true if the line is in correct format
     */

    public boolean handleLine(StatementTypes statementTypes, Matcher line)
            throws IllegalInitializedVariableException, IllegalAssignException,
            IllegalDeclarationException, IllegalFinalVariableException, IllegalIfWhileException,
            IllegalMethodCallException, IllegalEndOfScopesException, IllegalMethodDefinitionException {
        HandlersFactory handlersFactory = new HandlersFactory();
        HandlerInterface handle = handlersFactory.Handlers(statementTypes);
        if (handle == null)
            return true;
        return handle.handleLine(line);
    }

}
