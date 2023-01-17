package oop.ex6.handlers;

import oop.ex6.main.StatementTypes;
import oop.ex6.main.Types;

import java.io.IOException;
import java.util.regex.Matcher;



public class LineHandler {

    private VariableHandler variableHandler;
    private GlobalVariableHandler globalVariableHandler;
    public LineHandler(VariableHandler variableHandler){
        this.variableHandler = variableHandler;
    }


    public void handleLine(StatementTypes statementTypes, Types lineTypes, Matcher line) throws Exception {
        switch (statementTypes){
            case ASSIGN :
                String[] lines = line.group(0).replaceAll("\\s+", " ").split("\\s*,\\s*");
                for (int i = 1; i < lines.length; i++) {
                    String[] linesAndValues = lines[i].split("=");
                    if (linesAndValues.length != 2) throw new Exception("regex error");
                    if (i == lines.length -1) linesAndValues[1].replace(";","");
                    variableHandler.handleAssignedVariable(linesAndValues[0],linesAndValues[1]);
                }
                break;
            case FINAL:
                break;
            case DECLARE:
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

    private String[] divideToGroups(String line){

    }

}
