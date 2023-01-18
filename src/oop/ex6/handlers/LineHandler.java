package oop.ex6.handlers;

import oop.ex6.MethodCall;
import oop.ex6.MethodScope;
import oop.ex6.main.StatementTypes;
import oop.ex6.main.Types;

import java.util.regex.Matcher;


public class LineHandler {

    private final VariableAssignmentHandler variableAssignmentHandler = new VariableAssignmentHandler();
    private final VariableDeclarationHandler variableDeclarationHandler = new VariableDeclarationHandler();
    private final MethodDefinitionHandler methodDefinitionHandler = new MethodDefinitionHandler();
    private final MethodCallHandler methodCallHandler = new MethodCallHandler();
    private final IfWhileHandler ifWhileHandler = new IfWhileHandler();
    private final EndScopeHandler endScopeHandler = new EndScopeHandler();
    private final ReturnHandler returnHandler = new ReturnHandler();

    public LineHandler() {
    }


    public boolean handleLine(StatementTypes statementTypes, Types lineTypes, Matcher line) throws Exception {
        boolean handled = false;
        switch (statementTypes) {
            case ASSIGN:
                handled = variableAssignmentHandler.handleVariable(line);
                MethodScope.setLastLineReturn(false);
                break;
            case FINAL:
            case DECLARE:
                handled = variableDeclarationHandler.handleVariable(line);
                MethodScope.setLastLineReturn(false);
                break;
            case WHILE:
            case IF:
                handled = ifWhileHandler.handleIfWhile(line);
                MethodScope.setLastLineReturn(false);
                break;
            case METHOD_CALL:
                handled = methodCallHandler.handleMethodCall(line);
                MethodScope.setLastLineReturn(false);
                break;
            case METHOD_DECLARE:
                handled = methodDefinitionHandler.handleMethodDefinition(line);
                MethodScope.setLastLineReturn(false);
                break;
            case RETURN:
                MethodScope.setLastLineReturn(true);
                handled = returnHandler.handleReturn(line);
                break;
            case END_SCOPE:
                handled = endScopeHandler.handleEndScope();
                MethodScope.setLastLineReturn(false);
                break;
            case COMMENT:
            case EMPTY_LINE:
                handled = true;
                break;
        }
        return handled;
    }

//    private boolean assignHandler(Matcher line) throws Exception {
//        String[] lines = line.group(0).replaceAll("\\s+", "").trim().split("\\s*,\\s*");
//        for (int i = 1; i < lines.length; i++) {
//            String[] linesAndValues = lines[i].split("=");
//            if (linesAndValues.length != 2) throw new Exception("regex error");
//            if (i == lines.length - 1)
//                linesAndValues[1] = linesAndValues[1].replace(";", "");
//            if (!variableHandler.handleAssignedVariable(linesAndValues[0], linesAndValues[1]))
//                return false;
//        }
//        return true;
//    }
//
//
//    private boolean finalDeclareHandler(Matcher line, boolean isFinal, Types lineTypes) throws Exception {
//        String[] lines = line.group(0).replaceAll("\\s+", " ").trim().split("\\s*,\\s*");
//        String[] declareAndFinal = lines[0].split(" ");
//        if (declareAndFinal.length < 2 || declareAndFinal.length > 4) throw new Exception("regex error");
//        if (isFinal) lines[0] = declareAndFinal[2];
//        else lines[0] = declareAndFinal[1];
//        for (int i = 1; i < lines.length; i++) {
//            lines[i] = lines[i].replaceAll(" ", "");
//            String[] linesAndValues = lines[i].split("=");
//            if (isFinal && linesAndValues.length != 2)
//                throw new Exception("regex error");
//            if (isFinal) {
//                if (!variableHandler.handleFinalVariable(linesAndValues[0], lineTypes, linesAndValues[1]))
//                    return false;
//            }
//            else if (linesAndValues.length == 2) {
//                if (!variableHandler.handleInitializedVariable(linesAndValues[0], lineTypes,
//                        linesAndValues[1]))
//                    return false;
//            }
//            else {
//                if (!variableHandler.handleDeclaredVariable(linesAndValues[0], lineTypes))
//                    return false;
//            }
//        }
//        return true;
//
//    }
}
