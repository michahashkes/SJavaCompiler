package oop.ex6.handlers;

import oop.ex6.main.StatementTypes;
import oop.ex6.main.Types;

import java.util.regex.Matcher;



public class LineHandler {

    private VariableHandler variableHandler;
    private GlobalVariableHandler globalVariableHandler;
    public LineHandler(VariableHandler variableHandler){
        this.variableHandler = variableHandler;
    }


    public void handleLine(StatementTypes statementTypes, Types lineTypes, Matcher line) {
        switch (statementTypes){
            case ASSIGN :
                line.group(2).replaceAll("\\s+", " ").split("\\s*,\\s*");
                for (int i = 1; i < line.groupCount(); i++) {
                    variableHandler.handleAssignedVariable(line.group(i),,lineTypes);
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
