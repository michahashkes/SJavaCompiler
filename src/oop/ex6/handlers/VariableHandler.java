package oop.ex6.handlers;

import oop.ex6.Method;
import oop.ex6.MethodScope;
import oop.ex6.Variable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableHandler {

    private boolean handleDeclaredVariable(String variableName, String variableType) {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    private boolean handleInitializedVariable(String variableName,
                                              String variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    private boolean handleAssignedVariable(String variableName, String variableValue) {
        if (canVariableBeAssigned(variableName, variableValue)) {
            Method currentMethod = MethodScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(variableName);
            if (variable == null) { // case of possible global variable, add it for use
                variable = new Variable(POSSIBLE_GLOBAL_VARIABLE, variableName, true, false);
                currentMethod.addVariable(variable);
            } else {
                variable.setInitialized();
            }
            return true;
        }
        return false;
    }

    private boolean handleFinalVariable(String variableName,
                                     String variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, true);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    private boolean canVariableBeDeclared(String variableName) {
        Method currentMethod = MethodScope.getCurrentMethod();
        int currentScope = currentMethod.getCurrentScope();

        int otherVariableScope = currentMethod.getVariableScope(variableName);

        if (otherVariableScope < 0 || currentScope > otherVariableScope)
            return true;

        return otherVariableScope != currentScope;
    }

    private boolean canVariableBeAssigned(String variableName, String variableValue) {
        Method currentMethod = MethodScope.getCurrentMethod();
        Variable variable = currentMethod.getVariable(variableName);
        String valueType = deriveValueType(variableValue);
        // TODO: add check if variable value is local variable if it is initialized

        if (variable == null) {
            if (valueType == POSSIBLE_GLOBAL_VARIABLE) {
                MethodScope.addPossibleGlobalVariable(variableValue);
            } else if (valueType == null) {
                return false;
            }
            MethodScope.addPossibleGlobalVariable(variableName);
            return true;
        }

        if (valueType == POSSIBLE_GLOBAL_VARIABLE) {
            MethodScope.addPossibleGlobalVariable(variableValue);
            return true;
        }

        return (valueType != null && (!variable.isFinal())
                && areValueTypesEqual(variable.getType(), valueType));
    }

    private boolean canVariableBeInitialized(String variableName, String variableType, String variableValue) {
        if (!canVariableBeDeclared(variableName))
            return false;
        String valueType = deriveValueType(variableValue);

        if (valueType == POSSIBLE_GLOBAL_VARIABLE) {
            MethodScope.addPossibleGlobalVariable(variableValue);
            return true;
        }

        return (valueType != null && areValueTypesEqual(variableType, valueType));
    }

    private boolean areValueTypesEqual(String variableType, String valueType) {
        if (variableType.equals(valueType))
            return true;
        if (variableType.equals("double") && valueType.equals("int"))
            return true;
        if (variableType.equals("boolean") && (valueType.equals("int") || valueType.equals("double")))
            return true;
        return false;
    }

    private String deriveValueType(String variableValue) {
        // TODO: return all possible types
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(variableValue);
        if (m.matches())
            return "int";

        p = Pattern.compile(VARIABLE_NAME_REGEX);
        m = p.matcher(variableValue);
        if (m.matches()) {
            // check if the value is a variable inside function, if so return its type
            Method currentMethod = MethodScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(variableValue);
            if (variable != null)
                return variable.getType();
            return POSSIBLE_GLOBAL_VARIABLE;
        }
        return null;
    }
}
