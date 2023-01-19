package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.VariableTypesUtils;
import oop.ex6.handlers.HandlerInterface;
import oop.ex6.main.regex.Types;

import java.util.regex.Matcher;

public class VariableDeclarationHandler implements HandlerInterface {

    private static final String FINAL = "final";

    VariableHandler variableHandler = new VariableHandler(new LocalVariableHandler(), new GlobalVariableHandler());

    /**
     * check if the variable is in correct format
     * @param matcher
     * @return if the variable is correct format else otherwise
     */
    public boolean handleVariable(Matcher matcher) {
        boolean isFinal = false;
        String[] declaration = matcher.group(1).replaceAll("\\s+", " ").trim().split(" ");
        if (declaration.length < 1 || declaration.length > 2)
            return false;
        if (declaration.length == 2) {
            if (!declaration[0].equals(FINAL))
                return false;
            isFinal = true;
        }
        Types variableType = isFinal ? VariableTypesUtils.deriveTypeFromType(declaration[1]) :
                VariableTypesUtils.deriveTypeFromType(declaration[0]);
        if (variableType == Types.UNSUPPORTED_TYPE)
            return false;
       return checkVariables(isFinal,matcher,variableType);

    }

    private boolean checkVariables(boolean isFinal, Matcher matcher, Types variableType){
        //replace all multiple spaces in one space
        String[] variables = matcher.group(2).replaceAll("\\s+", " ").trim().split("\\s*,\\s*");
        for (int i = 0; i < variables.length; i++) {
            variables[i] = variables[i].replaceAll(" ", "");
            String[] variableAndValue = variables[i].split("=");
            if (isFinal && variableAndValue.length != 2)
                return false;

            if (isFinal) {
                if (!variableHandler.handleFinalVariable(variableAndValue[0], variableType, variableAndValue[1]))
                    return false;
            }
            else if (variableAndValue.length == 2) {
                if (!variableHandler.handleInitializedVariable(variableAndValue[0], variableType,
                        variableAndValue[1]))
                    return false;
            }
            else {
                if (!variableHandler.handleDeclaredVariable(variableAndValue[0], variableType))
                    return false;
            }
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
