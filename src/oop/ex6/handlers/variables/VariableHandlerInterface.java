package oop.ex6.handlers.variables;

import oop.ex6.main.regex.Types;

public interface VariableHandlerInterface {

    public boolean handleDeclaredVariable(String variableName, Types variableType);

    public boolean handleAssignedVariable(String variableName, String variableValue);

    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue);

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue);

}
