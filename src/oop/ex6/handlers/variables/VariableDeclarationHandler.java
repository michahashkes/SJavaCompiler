package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.VariableTypesUtils;
import oop.ex6.handlers.HandlerInterface;
import oop.ex6.main.regex.Types;

import java.util.regex.Matcher;

public class VariableDeclarationHandler implements HandlerInterface {

    VariableHandler variableHandler = new VariableHandler(new LocalVariableHandler(), new GlobalVariableHandler());

    public boolean handleVariable(Matcher matcher) {
        boolean isFinal = false;
        String[] declaration = matcher.group(1).replaceAll("\\s+", " ").trim().split(" ");
        if (declaration.length < 1 || declaration.length > 2)
            return false;
        if (declaration.length == 2) {
            if (!declaration[0].equals("final"))
                return false;
            isFinal = true;
        }
        Types variableType = isFinal ? VariableTypesUtils.deriveTypeFromType(declaration[1]) :
                VariableTypesUtils.deriveTypeFromType(declaration[0]);
        if (variableType == Types.UNSUPPORTED_TYPE)
            return false;

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

    @Override
    public boolean handleLine(Matcher line) {
        boolean handle = handleVariable(line);
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}
