package oop.ex6.handlers;

import java.util.regex.Matcher;

public class VariableAssignmentHandler {

    VariableHandler variableHandler = new VariableHandler(new LocalVariableHandler(), new GlobalVariableHandler());

    public boolean handleVariable(Matcher matcher) {
        String[] lines = matcher.group(0).replaceAll("\\s+", "").trim().split("\\s*,\\s*");

        for (int i = 0; i < lines.length; i++) {
            String[] linesAndValues = lines[i].split("=");
            if (linesAndValues.length != 2)
                return false;
            if (i == lines.length - 1)
                linesAndValues[1] = linesAndValues[1].replace(";", "");
            if (!variableHandler.handleAssignedVariable(linesAndValues[0], linesAndValues[1]))
                return false;
        }
        return true;


    }

}
