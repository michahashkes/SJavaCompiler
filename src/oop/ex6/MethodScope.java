package oop.ex6;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodScope {
    static private String currentMethod;
    static private HashMap<String, Method> Methods;
    static private ArrayList<String> possibleGlobalVariables;

    static public void setCurrentMethod(String method) {
        currentMethod = method;
    }

    public static Method getCurrentMethod() {
        return Methods.get(currentMethod);
    }

    public static void addMethod(String methodName, Method method) {
        Methods.put(methodName, method);
    }

    public static ArrayList<String> getPossibleGlobalVariables() {
        return possibleGlobalVariables;
    }

    public static void addPossibleGlobalVariable(String variableValue) {
        possibleGlobalVariables.add(variableValue);
    }
}
