package oop.ex6.handlers.variables;

import oop.ex6.scopes.ScriptScope;
import oop.ex6.main.regex.Types;



public class VariableHandler implements VariableHandlerInterface {

    LocalVariableHandler localVariableHandler;
    GlobalVariableHandler globalVariableHandler;

    public VariableHandler(LocalVariableHandler localVariableHandler, GlobalVariableHandler globalVariableHandler){
        this.localVariableHandler = localVariableHandler;
        this.globalVariableHandler = globalVariableHandler;
    }

    @Override
    public boolean handleDeclaredVariable(String variableName, Types variableType) {
        if(ScriptScope.isInGlobalScope())
            return globalVariableHandler.handleDeclaredVariable(variableName,variableType);
        return localVariableHandler.handleDeclaredVariable(variableName,variableType);
    }

    @Override
    public boolean handleAssignedVariable(String variableName, String variableValue) {
       if(ScriptScope.isInGlobalScope())
           return globalVariableHandler.handleAssignedVariable(variableName,variableValue);
       return localVariableHandler.handleAssignedVariable(variableName,variableValue);
    }

    @Override
    public boolean handleInitializedVariable(String variableName, Types variableType, String variableValue) {
        if(ScriptScope.isInGlobalScope())
            return globalVariableHandler.handleInitializedVariable(variableName, variableType,variableValue);
        return localVariableHandler.handleInitializedVariable(variableName,variableType,variableValue);
    }

    @Override
    public boolean handleFinalVariable(String variableName, Types variableType, String variableValue) {
       if(ScriptScope.isInGlobalScope())
           return globalVariableHandler.handleFinalVariable(variableName,variableType,variableValue);
       return localVariableHandler.handleFinalVariable(variableName,variableType,variableValue);
    }
}
