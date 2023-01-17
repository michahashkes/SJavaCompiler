package oop.ex6.handlers;

import oop.ex6.main.Types;

public interface VariableHandlerInterface {

    public boolean handleDeclaredVariable(String variableName, Types variableType);

    public boolean handleAssignedVariable(String variableName, String variableValue);

    public boolean handleInitializedVariable(String variableName,
                                             Types variableType, String variableValue);

    public boolean handleFinalVariable(String variableName,
                                       Types variableType, String variableValue);

}
