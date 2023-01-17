package oop.ex6;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodScope {
    static private boolean isInGlobalScope = true;
    static private boolean isLastLineReturn = false;
    static private String currentMethod;
    static private final HashMap<String, Method> methods = new HashMap<>();
    static private final HashMap<String, ArrayList<Variable>> methodCalls = new HashMap<>();
    static private final HashMap<String, Variable> globalVariables = new HashMap<>();
    static private final ArrayList<PossibleGlobalVariable> possibleGlobalVariables = new ArrayList<>();

    static public void setCurrentMethod(String method) {
        currentMethod = method;
    }

    public static Method getCurrentMethod() {
        return methods.get(currentMethod);
    }

    public static void addMethod(String methodName, Method method) {
        methods.put(methodName, method);
    }

    public static HashMap<String, Method> getMethods() {
        return methods;
    }

    public static ArrayList<PossibleGlobalVariable> getPossibleGlobalVariables() {
        return possibleGlobalVariables;
    }

    public static void addPossibleGlobalVariable(PossibleGlobalVariable possibleGlobalVariable) {
        possibleGlobalVariables.add(possibleGlobalVariable);
    }

    public static void addGlobalVariable(Variable variable) {
        globalVariables.put(variable.getName(), variable);
    }

    public static HashMap<String, Variable> getGlobalVariables() {
        return globalVariables;
    }

    public static boolean isInGlobalScope() {
        return isInGlobalScope;
    }

    public static void setInGlobalScope(boolean isInGlobalScope) {
        MethodScope.isInGlobalScope = isInGlobalScope;
    }

    public static boolean isLastLineReturn() {
        return isLastLineReturn;
    }

    public static void setLastLineReturn(boolean isLastLineReturn) {
        MethodScope.isLastLineReturn = isLastLineReturn;
    }
}
