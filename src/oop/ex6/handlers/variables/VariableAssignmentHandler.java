package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.handlers.HandlerInterface;

import java.util.regex.Matcher;

public class VariableAssignmentHandler implements HandlerInterface {

    VariableHandler variableHandler = new VariableHandler(new LocalVariableHandler(), new GlobalVariableHandler());

    /**
     * check if variable is correct
     * @param matcher - phase
     * @return true if the variable is true
     */
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

    /**
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */
    @Override
    public boolean handleLine(Matcher line) {
        boolean handle = handleVariable(line);
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}
