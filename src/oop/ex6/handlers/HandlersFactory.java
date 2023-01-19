package oop.ex6.handlers;

import oop.ex6.handlers.scopes.*;
import oop.ex6.handlers.variables.VariableAssignmentHandler;
import oop.ex6.handlers.variables.VariableDeclarationHandler;
import oop.ex6.main.regex.StatementTypes;

public class HandlersFactory {

    /*
    return handler follow statementTypes
     */
    public HandlerInterface Handlers(StatementTypes statementTypes){
        switch (statementTypes) {
            case ASSIGN :
                return new VariableAssignmentHandler();
            case FINAL:
            case DECLARE :
                return new VariableDeclarationHandler();
            case WHILE:
            case IF :
                return new IfWhileHandler();
            case METHOD_CALL :
                return new MethodCallHandler();
            case METHOD_DECLARE :
                return new MethodDefinitionHandler();
            case RETURN :
                return new ReturnHandler();
            case END_SCOPE :
                return new EndScopeHandler();
            case COMMENT:
            case EMPTY_LINE:
                return null;
        }
        return null;
    }

}
