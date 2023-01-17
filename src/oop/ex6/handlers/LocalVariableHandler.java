package oop.ex6.handlers;

import oop.ex6.*;
import oop.ex6.main.Types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oop.ex6.main.RegexGlobals.NAME_FORMAT;

public class LocalVariableHandler implements VariableHandler {

    public boolean handleDeclaredVariable(String variableName, Types variableType) {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    public boolean handleAssignedVariable(String variableName, String variableValue) {
        if (canVariableBeAssigned(variableName, variableValue)) {
            Method currentMethod = MethodScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(variableName);
            if (variable == null) { // case of possible global variable, add it for use
                variable = new Variable(Types.POSSIBLE_VARIABLE, variableName, true, false);
                currentMethod.addVariable(variable);
            } else {
                variable.setInitialized();
            }
            return true;
        }
        return false;
    }

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, true);
            Method currentMethod = MethodScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    public boolean canVariableBeDeclared(String variableName) {
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
        // TODO: add check if milim shmurot

        if (variable != null) {
            // local variable was declared
            if (VariableTypesUtils.isValueVariable(variableValue)) {
                // value is in shape of variable, check if it is local
                Variable variableToAssign = currentMethod.getVariable(variableValue);
                if (variableToAssign == null) {
                    // variable value is not local, add it to possible list and
                    // return true because we don't know type
                    PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variableValue);
                    possibleGlobalVariableAssigned.setHasType(true);
                    possibleGlobalVariableAssigned.setValueType(variable.getType());
                    MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                    return true;
                }
                // variable value is local, and check if it was initialized, and if types equal
                return variableToAssign.isInitialized() &&
                        (!variable.isFinal()) &&
                        VariableTypesUtils.areValueTypesEqual(variable.getType(), variableToAssign.getType());
            }
            // value is regular shape (primitive type), derive its type and return if equal
            Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
            return ((!variable.isFinal()) && VariableTypesUtils.areValueTypesEqual(variable.getType(), valueType));
        }

        // variable is not local, could be global
        PossibleGlobalVariable possibleGlobalVariableToAssign = new PossibleGlobalVariable(variableName);
        possibleGlobalVariableToAssign.setAssigned(true);

        if (VariableTypesUtils.isValueVariable(variableValue)) {
            // value is in shape of variable, check if it is local
            Variable variableToAssign = currentMethod.getVariable(variableValue);
            if (variableToAssign == null) {
                // variable value is not local, add both it and variable to possible list and
                // return true because we don't know types
                PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variableValue);
                possibleGlobalVariableAssigned.setValueName(variableName);
                MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                possibleGlobalVariableToAssign.setValueName(variableValue);
                MethodScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
                return true;
            }
            // local value exits, check if it is initialized
            if (!variableToAssign.isInitialized())
                return false;
            // value initialized, add variable to possible list
            possibleGlobalVariableToAssign.setHasType(true);
            possibleGlobalVariableToAssign.setValueType(variableToAssign.getType());
            MethodScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
            return true;
        }
        // value is primitive type, derive its type and add variable to possible list
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        possibleGlobalVariableToAssign.setHasType(true);
        possibleGlobalVariableToAssign.setValueType(valueType);
        MethodScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
        return true;
    }

    private boolean canVariableBeInitialized(String variableName,
                                             Types variableType, String variableValue) {
        if (!canVariableBeDeclared(variableName))
            return false;
        Method currentMethod = MethodScope.getCurrentMethod();

        if (VariableTypesUtils.isValueVariable(variableValue)) {
            // value is in shape of variable, check if it is local
            Variable variableToAssign = currentMethod.getVariable(variableValue);
            if (variableToAssign == null) {
                // variable value is not local, add it to possible list and
                // return true because we don't know type
                PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variableValue);
                possibleGlobalVariableAssigned.setHasType(true);
                possibleGlobalVariableAssigned.setValueType(variableType);
                MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                return true;
            }
            return variableToAssign.isInitialized() &&
                    VariableTypesUtils.areValueTypesEqual(variableType, variableToAssign.getType());
        }
        // value is regular shape (primitive type), derive its type and return if equal
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        return VariableTypesUtils.areValueTypesEqual(variableType, valueType);
    }
}
