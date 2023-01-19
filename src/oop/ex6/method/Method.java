package oop.ex6.method;

import oop.ex6.variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Method {
    private String name;
    private ArrayList<Variable> parameters;
    private final HashMap<Integer, ArrayList<Variable>> variablesInScopesMap = new HashMap<>();
    private int currentScope = 0;
    private boolean functionClosed = false;

    public Method(String name, ArrayList<Variable> parameters) {
        this.name = name;
        this.parameters = parameters;

        variablesInScopesMap.put(currentScope, new ArrayList<Variable>());
        for (Variable variable : parameters) {
            variablesInScopesMap.get(currentScope).add(variable);
        }
    }

    public String getName() {
        return name;
    }

    public int getCurrentScope() {
        return currentScope;
    }

    public ArrayList<Variable> getParameters() {
        return parameters;
    }

    public void addScope() {
        currentScope++;
        variablesInScopesMap.put(currentScope, new ArrayList<>());
    }

    public void removeScope() {
        variablesInScopesMap.remove(currentScope);
        currentScope--;
    }

    public void addVariable(Variable variable) {
        variablesInScopesMap.get(currentScope).add(variable);
    }

    public int getVariableScope(String variableName) {
        for (Map.Entry<Integer, ArrayList<Variable>> entry: variablesInScopesMap.entrySet()) {
            for (Variable variable : entry.getValue()) {
                if (variableName.equals(variable.getName()))
                    return entry.getKey();
            }
        }
        return -1;
    }

    public Variable getVariable(String variableName) {
        int variableScope = getVariableScope(variableName);
        if (variableScope < 0)
            return null;

        for (Variable variable : variablesInScopesMap.get(variableScope)) {
            if (variableName.equals(variable.getName()))
                return variable;
        }
        return null;
    }

    public boolean isFunctionClosed() {
        return functionClosed;
    }

    public void setFunctionClosed(boolean functionClosed) {
        this.functionClosed = functionClosed;
    }
}
