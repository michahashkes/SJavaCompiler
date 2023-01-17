package oop.ex6.handlers;

import oop.ex6.main.StatementTypes;
import oop.ex6.main.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.regex.Matcher;


public class LineHandler {

    private VariableHandler variableHandler;
    private GlobalVariableHandler globalVariableHandler;

    public LineHandler(VariableHandler variableHandler) {
        this.variableHandler = variableHandler;
    }


    public void handleLine(StatementTypes statementTypes, Types lineTypes, Matcher line) throws Exception {
        switch (statementTypes) {
            case ASSIGN:
                assignHandler(line);
                break;
            case FINAL:
                finalDeclareHandler(line, true, lineTypes);
                break;
            case DECLARE:
                finalDeclareHandler(line, false, lineTypes);
                break;
            case IF:
                break;
            case WHILE:
                break;
            case METHOD_CALL:
                break;
            case METHOD_DECLARE:
                break;

        }
    }

    private void assignHandler(Matcher line) throws Exception {
        String[] lines = line.group(0).replaceAll("\\s+", "").split("\\s*,\\s*");
        for (int i = 1; i < lines.length; i++) {
            String[] linesAndValues = lines[i].split("=");
            if (linesAndValues.length != 2) throw new Exception("regex error");
            if (i == lines.length - 1) linesAndValues[1] = linesAndValues[1].replace(";", "");
            variableHandler.handleAssignedVariable(linesAndValues[0], linesAndValues[1]);
        }
    }


    private void finalDeclareHandler(Matcher line, boolean isFinal, Types lineTypes) throws Exception {
        String[] lines = line.group(0).replaceAll("\\s+", " ").split("\\s*,\\s*");
        String[] declareAndFinal = lines[0].split(" ");
        if (declareAndFinal.length < 2 || declareAndFinal.length > 3) throw new Exception("regex error");
        if (isFinal) lines[0] = declareAndFinal[2];
        else lines[0] = declareAndFinal[1];
        for (int i = 1; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll(" ", "");
            String[] linesAndValues = lines[i].split("=");
            if (isFinal && linesAndValues.length != 2) throw new Exception("regex error");
            if (isFinal) variableHandler.handleFinalVariable(linesAndValues[0], lineTypes, linesAndValues[1]);
            else if (linesAndValues.length == 2) variableHandler.handleInitializedVariable(linesAndValues[0], lineTypes,
                    linesAndValues[1]);
            else variableHandler.handleDeclaredVariable(linesAndValues[0], lineTypes);
        }


    }
}
