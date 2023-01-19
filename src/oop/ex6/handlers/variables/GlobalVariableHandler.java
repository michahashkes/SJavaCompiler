package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;
import oop.ex6.main.regex.Types;

public class GlobalVariableHandler implements VariableHandlerInterface {

    /**
     * check if line of declaration variable is correct
     * @param variableName the type of the variable
     * @param variableType the type of declaration
     * @return true if is correct else otherwise
     */
    public boolean handleDeclaredVariable(String variableName, Types variableType) throws IllegalDeclarationException {
        if (canVariableBeDeclared(variableName)) {
            Variable variable = new Variable(variableType, variableName, false, false);
            ScriptScope.addGlobalVariable(variable);
            return true;
        }
        throw new IllegalDeclarationException();
    }

    /**
     * check if the assignment of variable is in correct format
     * @param variableName
     * @param variableValue
     * @return true if is correct else otherwise
     */

    public boolean handleAssignedVariable(String variableName, String variableValue) throws IllegalAssignException {
        if (canVariableBeAssigned(variableName, variableValue)) {
            Variable variable = getGlobalVariable(variableName);
            variable.setInitialized();
            return true;
        }
        throw new IllegalAssignException();
    }

    /**
     * check if initialized variable is in correct format
     * @param variableName
     * @param variableType
     * @param variableValue
     * @return true if is correct else otherwise
     */

    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue) throws IllegalInitializedVariableException {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, false);
            ScriptScope.addGlobalVariable(variable);
            return true;
        }
        throw new IllegalInitializedVariableException();
    }

    /**
     * check if final variable is in correct format
     * @param variableName type of string
     * @param variableType type of Types
     * @param variableValue type of string
     * @return true if is correct else otherwise
     */

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue) throws IllegalFinalVariableException {
        if (canVariableBeInitialized(variableName, variableType, variableValue)) {
            Variable variable = new Variable(variableType, variableName, true, true);
            ScriptScope.addGlobalVariable(variable);
            return true;
        }
        throw new IllegalFinalVariableException();
    }

    /*
    check that variable has not declared before
     */

    private boolean canVariableBeDeclared(String variableName) {
        return getGlobalVariable(variableName) == null;
    }

    /**
     * check if variable can be assigned
     * @param variableName type of string
     * @param variableValue type of string
     * @return true if is correct else otherwise
     */

    private boolean canVariableBeAssigned(String variableName, String variableValue) throws IllegalAssignException {
        Variable variable = getGlobalVariable(variableName);
        if (variable == null)
            return false;
        //check if the assign value is a global variable
        if (VariableTypesUtils.isValueVariable(variableValue)) {
            Variable variableToAssign = getGlobalVariable(variableValue);
            if (variableToAssign == null)
                throw new IllegalAssignException();
            return variableToAssign.isInitialized() && (!variable.isFinal())
                    && VariableTypesUtils.areValueTypesEqual(variable.getType(), variableToAssign.getType());
        }

        // value is regular shape (primitive type), derive its type and return if equal
        Types valueType = VariableTypesUtils.deriveTypeFromValue(variableValue);
        return (!variable.isFinal()) && VariableTypesUtils.areValueTypesEqual(variable.getType(), valueType);
    }

    /**
     * check if variable can be initialized
     * @param variableName String
     * @param variableType Types
     * @param variableValue String
     * @return true if is correct else otherwise
     */

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

    /**
     * return global variable
     * @param variableName
     * @return variable
     */

    public Variable getGlobalVariable(String variableName) {
        return ScriptScope.getGlobalVariables().get(variableName);
    }
}
