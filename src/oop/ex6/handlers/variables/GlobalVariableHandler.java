package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;
import oop.ex6.main.regex.Types;

public class GlobalVariableHandler implements VariableHandlerInterface {

    public boolean handleDeclaredVariable(String variableName, Types variableType) {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            ScriptScope.addGlobalVariable(variable);
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
                                             Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            ScriptScope.addGlobalVariable(variable);
            return true;
        }
        return false;
    }

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, true);
            ScriptScope.addGlobalVariable(variable);
            return true;
        }
        return false;
    }

    private boolean canVariableBeDeclared(String variableName) {
        return getGlobalVariable(variableName) == null;
    }

    private boolean canVariableBeAssigned(String variableName, String variableValue) {
        Variable variable = getGlobalVariable(variableName);
        if (variable == null)
            return false;

        if (VariableTypesUtils.isValueVariable(variableValue)) {
            Variable variableToAssign = getGlobalVariable(variableValue);
            if (variableToAssign == null)
                return false;
            return variableToAssign.isInitialized() && (!variable.isFinal())
                    && VariableTypesUtils.areValueTypesEqual(variable.getType(), variableToAssign.getType());
        }

        // value is regular shape (primitive type), derive its type and return if equal
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        return (!variable.isFinal()) && VariableTypesUtils.areValueTypesEqual(variable.getType(), valueType);
    }

    private boolean canVariableBeInitialized(String variableName,
                                             Types variableType, String variableValue) {
        if (getGlobalVariable(variableName) != null)
            return false;

        if (VariableTypesUtils.isValueVariable(variableValue)) {
            Variable variableToAssign = getGlobalVariable(variableValue);
            if (variableToAssign == null)
                return false;
            return variableToAssign.isInitialized() &&
                    VariableTypesUtils.areValueTypesEqual(variableType, variableToAssign.getType());
        }

        // value is regular shape (primitive type), derive its type and return if equal
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        return VariableTypesUtils.areValueTypesEqual(variableType, valueType);
    }

    public Variable getGlobalVariable(String variableName) {
        return ScriptScope.getGlobalVariables().get(variableName);
//        for (Variable variable : MethodScope.getGlobalVariables()) {
//            if (variableName.equals(variable.getName()))
//                return variable;
//        }
//        return null;
    }
}
