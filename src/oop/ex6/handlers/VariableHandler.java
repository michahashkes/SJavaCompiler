package oop.ex6.handlers;

import java.lang.reflect.Type;

public interface VariableHandler {

    public boolean handleDeclaredVariable(String variableName, Type variableType);

    public boolean handleAssignedVariable(String variableName, String variableValue);

    public boolean handleInitializedVariable(String variableName,
                                             Type variableType, String variableValue);

    public boolean handleFinalVariable(String variableName,
                                       Type variableType, String variableValue);

}
