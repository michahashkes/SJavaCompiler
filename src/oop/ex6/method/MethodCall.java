package oop.ex6.method;

import oop.ex6.main.regex.Types;

import java.util.ArrayList;

public class MethodCall {

    private String name;

    public class MethodCallArgument {
        private final boolean isPrimitive;
        private final Types variableType;
        private final String variableName;

        public MethodCallArgument(boolean isPrimitive, Types variableType, String variableName) {
            this.isPrimitive = isPrimitive;
            this.variableType = variableType;
            this.variableName = variableName;
        }

        public boolean isPrimitive() {
            return isPrimitive;
        }

        public Types getType() {
            return variableType;
        }

        public String getName() {
            return variableName;
        }
    }
    private ArrayList<MethodCallArgument> methodCallVariables = new ArrayList<>();

    public MethodCall(String name) {
        this.name = name;
    }

    public void addArgument(boolean isPrimitive, Types variableType, String variableName) {
        methodCallVariables.add(new MethodCallArgument(isPrimitive, variableType, variableName));
    }

    public ArrayList<MethodCallArgument> getMethodCallVariables() {
        return methodCallVariables;
    }

}
