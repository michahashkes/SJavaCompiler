package oop.ex6.handlers;

public interface VariableHandler {

    public boolean handleDeclaredVariable(String variableName, String variableType);
    public boolean handleAssignedVariable(String variableName, String variableValue);
    public boolean handleInitializedVariable(String variableName,
                                             String variableType, String variableValue);
    public boolean handleFinalVariable(String variableName,
                                       String variableType, String variableValue);

}
