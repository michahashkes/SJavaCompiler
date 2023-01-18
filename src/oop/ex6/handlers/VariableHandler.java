package oop.ex6.handlers;

import oop.ex6.MethodScope;
import oop.ex6.main.Types;

import java.util.regex.Matcher;

public class VariableHandler implements VariableHandlerInterface{

    LocalVariableHandler localVariableHandler;
    GlobalVariableHandler globalVariableHandler;

    public VariableHandler(LocalVariableHandler localVariableHandler, GlobalVariableHandler globalVariableHandler){
        this.localVariableHandler = localVariableHandler;
        this.globalVariableHandler = globalVariableHandler;
    }

    @Override
    public boolean handleDeclaredVariable(String variableName, Types variableType) {
        if(MethodScope.isInGlobalScope())
            return globalVariableHandler.handleDeclaredVariable(variableName,variableType);
        return localVariableHandler.handleDeclaredVariable(variableName,variableType);
    }

    @Override
    public boolean handleAssignedVariable(String variableName, String variableValue) {
       if(MethodScope.isInGlobalScope())
           return globalVariableHandler.handleAssignedVariable(variableName,variableValue);
       return localVariableHandler.handleAssignedVariable(variableName,variableValue);
    }

    @Override
    public boolean handleInitializedVariable(String variableName, Types variableType, String variableValue) {
        if(MethodScope.isInGlobalScope())
            return globalVariableHandler.handleInitializedVariable(variableName, variableType,variableValue);
        return localVariableHandler.handleInitializedVariable(variableName,variableType,variableValue);
    }

    @Override
    public boolean handleFinalVariable(String variableName, Types variableType, String variableValue) {
       if(MethodScope.isInGlobalScope())
           return globalVariableHandler.handleFinalVariable(variableName,variableType,variableValue);
       return localVariableHandler.handleFinalVariable(variableName,variableType,variableValue);
    }
}
