package oop.ex6.handlers.variables;

import oop.ex6.main.regex.Types;

public interface VariableHandlerInterface {
    /**
     * handle with declared variable
     * @param variableName String
     * @param variableType Types
     * @return true if the declared variable are in correct format
     */
    public boolean handleDeclaredVariable(String variableName, Types variableType);
    /**
     * handle with assigned variable
     * @param variableName String
     * @param variableValue Types
     * @return true if the assigned variable are in correct format
     */

    public boolean handleAssignedVariable(String variableName, String variableValue);
    /**
     * handle with initialized variable
     * @param variableName String
     * @param variableType Types
     * @return true if theinitialized variable are in correct format
     */
    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue);
    /**
     * handle with final variable
     * @param variableName String
     * @param variableType Types
     * @return true if the final variable are in correct format
     */

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue);

}
