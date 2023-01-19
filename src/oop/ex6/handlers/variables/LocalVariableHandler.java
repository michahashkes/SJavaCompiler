package oop.ex6.handlers.variables;

import oop.ex6.main.regex.Types;
import oop.ex6.method.Method;
import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.PossibleGlobalVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;

public class LocalVariableHandler implements VariableHandlerInterface {

    /**
     * handle with declared variable
     * @param variableName String
     * @param variableType Types
     * @return true if the declared variable are in correct format
     */
    public boolean handleDeclaredVariable(String variableName, Types variableType) {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            Method currentMethod = ScriptScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }
    /**
     * handle with initialized variable
     * @param variableName String
     * @param variableType Types
     * @return true if the initialized variable are in correct format
     */

    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            Method currentMethod = ScriptScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }
    /**
     * handle with assigned variable
     * @param variableName String
     * @param variableValue Types
     * @return true if the assigned variable are in correct format
     */


    public boolean handleAssignedVariable(String variableName, String variableValue) {
        if (canVariableBeAssigned(variableName, variableValue)) {
            Method currentMethod = ScriptScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(variableName);
            if (variable == null) { // case of possible global variable, add it for use
                variable = new Variable(Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED,
                        variableName, true, false);
                currentMethod.addPotentialGlobalVariable(variable);
            } else {
                variable.setInitialized();
            }
            return true;
        }
        return false;
    }
    /**
     * handle with final variable
     * @param variableName String
     * @param variableType Types
     * @return true if the final variable are in correct format
     */


    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue) {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, true);
            Method currentMethod = ScriptScope.getCurrentMethod();
            currentMethod.addVariable(variable);
            return true;
        }
        return false;
    }

    /*
    check if variable can be declared
    return true if he can be, false otherwise
     */

    public boolean canVariableBeDeclared(String variableName) {
        Method currentMethod = ScriptScope.getCurrentMethod();
        int currentScope = currentMethod.getCurrentScope();

        int otherVariableScope = currentMethod.getVariableScope(variableName);

        if (otherVariableScope < 0 || currentScope > otherVariableScope)
            return true;

        return otherVariableScope != currentScope;
    }

    /*
    check if variable can be assigned, return true if he can false otherwise
     */

    private boolean canVariableBeAssigned(String variableName, String variableValue) {
        Method currentMethod = ScriptScope.getCurrentMethod();
        Variable variable = currentMethod.getVariable(variableName);

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
                    ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                    return true;
                }
                // variable value is local, and check if it was initialized, and if types equal
                return variableToAssign.isInitialized() &&
                        (!variable.isFinal()) &&
                        VariableTypesUtils.areValueTypesEqual(variable.getType(), variableToAssign.getType());
            }
            // value is regular shape (primitive type), derive its type and return if equal
            Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
            return (!variable.isFinal()) && VariableTypesUtils.areValueTypesEqual(variable.getType(), valueType);
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
                ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                possibleGlobalVariableToAssign.setValueName(variableValue);
                ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
                return true;
            }
            // local value exits, check if it is initialized
            if (!variableToAssign.isInitialized())
                return false;
            // value initialized, add variable to possible list
            possibleGlobalVariableToAssign.setHasType(true);
            possibleGlobalVariableToAssign.setValueType(variableToAssign.getType());
            ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
            return true;
        }
        // value is primitive type, derive its type and add variable to possible list
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        possibleGlobalVariableToAssign.setHasType(true);
        possibleGlobalVariableToAssign.setValueType(valueType);
        ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableToAssign);
        return true;
    }



    /*
    check if variable can be initialized, return true if he can false otherwise
     */

    private boolean canVariableBeInitialized(String variableName,
                                             Types variableType, String variableValue) {
        if (!canVariableBeDeclared(variableName))
            return false;
        Method currentMethod = ScriptScope.getCurrentMethod();

        if (VariableTypesUtils.isValueVariable(variableValue)) {
            // value is in shape of variable, check if it is local
            Variable variableToAssign = currentMethod.getVariable(variableValue);
            if (variableToAssign == null) {
                // variable value is not local, add it to possible list and
                // return true because we don't know type
                PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variableValue);
                possibleGlobalVariableAssigned.setHasType(true);
                possibleGlobalVariableAssigned.setValueType(variableType);
                ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
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
