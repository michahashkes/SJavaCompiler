package oop.ex6.handlers;

import oop.ex6.*;
import oop.ex6.main.Types;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodCallHandler {

    public boolean handleMethodCall(Matcher matcher) {
        if (MethodScope.isInGlobalScope())
            return false;

        String callMethodName = matcher.group(1);

        String[] methodArguments;
        if (matcher.groupCount() < 2 || matcher.group(2) == null || matcher.group(2).isEmpty())
            methodArguments = new String[0];
        else
            methodArguments =
                    matcher.group(2).replaceAll("\\s+", " ").trim().split("\\s*,\\s*");

        MethodCall methodCall = new MethodCall(callMethodName);
        for (String argument : methodArguments) {
            Types argumentType = getArgumentType(argument);

            if (argumentType == Types.UNSUPPORTED_TYPE)
                return false;

            if (argumentType == Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED ||
                    argumentType == Types.POSSIBLE_GLOBAL_VARIABLE_UNINITIALIZED)
                methodCall.addArgument(false, argumentType, argument);
            else
                methodCall.addArgument(true, argumentType, "");

        }

        MethodScope.addMethodCall(callMethodName, methodCall);
        return true;
    }

    private Types getArgumentType(String argument) {
        Types argumentType = VariableTypesUtils.deriveTypeFromValue(argument);

        if (argumentType == Types.POSSIBLE_VARIABLE) {
            Method currentMethod = MethodScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(argument);

            if (variable != null) {
                if (variable.getType() == Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED) {
                    // variable exists, but it is probably global variable
                    PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variable.getName());
                    possibleGlobalVariableAssigned.setInMethodCall(true);
                    possibleGlobalVariableAssigned.setInitializedLocally(variable.isInitialized());
                    MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                    return Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED;
                }
                if (!variable.isInitialized())
                    return Types.UNSUPPORTED_TYPE;
                return variable.getType();
            }

            // could be global variable, check for later
            PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(argument);
            possibleGlobalVariableAssigned.setInMethodCall(true);
            MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
            return Types.POSSIBLE_GLOBAL_VARIABLE_UNINITIALIZED;
        }

        return argumentType;
    }

}
