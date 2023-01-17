package oop.ex6.handlers;

import oop.ex6.Method;
import oop.ex6.MethodScope;
import oop.ex6.Variable;

public class GlobalVariableHandler {

    public boolean handleDeclaredVariable(String variableName, String variableType) {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            MethodScope.addGlobalVariable(variable);
            return true;
        }
        return false;
    }

    public boolean handleAssignedVariable(String variableName, String variableValue) {
        if (canVariableBeAssigned(variableName, variableValue)) {
            Variable variable = getGlobalVariable(variableName);
            variable.setInitialized();
            return true;
        }
        return false;
    }

    public boolean handleInitializedVariable(String variableName,
                                             String variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            MethodScope.addGlobalVariable(variable);
            return true;
        }
        return false;
    }

    private boolean canVariableBeDeclared(String variableName) {
        return getGlobalVariable(variableName) != null;
    }

    private boolean canVariableBeAssigned(String variableName, String variableValue) {
        Variable variable = getGlobalVariable(variableName);
        if (variable == null)
            return false;

        if (isValueVariable(variableValue)) {
            Variable variableToAssign = getGlobalVariable(variableValue);
            if (variableToAssign == null)
                return false;
            return variableToAssign.isInitialized() && (!variable.isFinal())
                    && areValueTypesEqual(variable.getType(), variableToAssign.getType());
        }

        // value is regular shape (primitive type), derive its type and return if equal
        String valueType = deriveValueType(variableValue);
        return ((!variable.isFinal()) && areValueTypesEqual(variable.getType(), valueType));
    }

    private boolean canVariableBeInitialized(String variableName,
                                             String variableType, String variableValue) {
        if (getGlobalVariable(variableName) != null)
            return false;

        if (isValueVariable(variableValue)) {
            Variable variableToAssign = getGlobalVariable(variableValue);
            if (variableToAssign == null)
                return false;
            return variableToAssign.isInitialized() &&
                    areValueTypesEqual(variableType, variableToAssign.getType());
        }

        // value is regular shape (primitive type), derive its type and return if equal
        String valueType = deriveValueType(variableValue);
        return areValueTypesEqual(variableType, valueType);
    }

    public Variable getGlobalVariable(String variableName) {
        return MethodScope.getGlobalVariables().get(variableName);
//        for (Variable variable : MethodScope.getGlobalVariables()) {
//            if (variableName.equals(variable.getName()))
//                return variable;
//        }
//        return null;
    }
}
